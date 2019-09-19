package com.shakiba.xtranslation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.shakiba.xtranslation.surah.SurahFragment;
import com.util.ShowAlertDialog;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity implements ShowAlertDialog.OnRadioButtonClick{


    String username, address;
    ArrayList<String> users = new ArrayList();
    int port;
    Boolean isConnected = false;
    String TAG="UserCheck";
    ShowAlertDialog dialog;
    Socket sock;
    BufferedReader reader;
    private ImageView leftScroll;
    private ImageView rightScroll;
    private Button surahSecltionButton;
    private EditText tf_chat;
    PrintWriter writer;
    private TextView ta_chat;
    private Switch logSwitch;
    private Button connectBtn;
    private Handler handler;
    private SurahFragment surahFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        handler=new Handler();
        connectBtn=findViewById(R.id.connect_button);
        dialog=new ShowAlertDialog(this);
        dialog.setOnRadioButtonClick(this);
        ta_chat=findViewById(R.id.logText);
        ta_chat.setMovementMethod(new ScrollingMovementMethod());
        logSwitch=findViewById(R.id.logSwitch);
        tf_chat=findViewById(R.id.inputText);
        leftScroll=findViewById(R.id.imageView);
        rightScroll=findViewById(R.id.imageView2);
        surahSecltionButton=findViewById(R.id.btnSelection);
        surahFragment=new SurahFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.changeView,surahFragment).commit();
        logSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    ta_chat.setVisibility(View.VISIBLE);
                } else {
                    // The toggle is disabled
                    ta_chat.setVisibility(View.INVISIBLE);
                }
            }
        });
        leftScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rightScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        surahSecltionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogInActivity.this,SurahActivity.class);
                startActivity(intent);
            }
        });

    }
    //--------------------------//

    public void ListenThread()
    {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    //--------------------------//

    public void userAdd(String data)
    {
        users.add(data);
    }
    public void userRemove(String data)
    {
       // ta_chat.append(data + " is now offline.\n");
    }

    //--------------------------//

    public void writeUsers()
    {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token:tempList)
        {
            //users.append(token + "\n");
        }
    }

    //--------------------------//

    public void sendDisconnect()
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye);
            writer.flush();
        } catch (Exception e)
        {
            //ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//

    public void Disconnect()
    {
        try
        {
            //ta_chat.append("Disconnected.\n");
            sock.close();
            connectBtn.setText("Connect");
        } catch(Exception ex) {
            //ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        //tf_username.setEditable(true);

    }

    @Override
    public void onButtonClick(String username,String address,int port) {

            LogInClick(username,address,port);


    }

    public void ConnectClick(View view) {
        if(!isConnected){
            dialog.show();
        }else {
            disconnectSocket();
        }

    }

    public void leftBtnClick(View view) {

    }

    public void rightBtnClick(View view) {

    }

    public void sendClick(View view) {
        String msg=tf_chat.getText().toString().trim();
        if(!msg.isEmpty())
        {
            sendChat(msg);
        }
    }


    //--------------------------//
    String[] data;
    String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==111)
        {

        }
    }

    public class IncomingReader implements Runnable
    {
        @Override
        public void run()
        {


            try
            {
                while ((stream = reader.readLine()) != null)
                {
                    // String[] serverResponse=stream.split("-");
                   // System.out.println(stream.length());

                            Log.d(TAG, "Len:"+stream.length()+"Msg:"+stream+"\n");
                    //Toast.makeText(LogInActivity.this, "", Toast.LENGTH_SHORT).show();
                            //                   ta_chat.append("Len:"+stream.length()+"Msg:"+stream+"\n");
//                    ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                            data = stream.split(":");

                            if (data[2].equals(chat))
                            {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {


                                        Log.d(TAG, "Check: "+data[0] + ": " + data[1] );
                                        String string=ta_chat.getText().toString().trim();
                                        final String str=string;

                                        ta_chat.setText(str + "\r\n"+ data[0] + ": " + data[1] + "\r\n");
                                        if(data[0].contains("server"))
                                        {
                                            Toast.makeText(LogInActivity.this, ""+data[0] + ": " + data[1], Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                                //ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                            }
                            else if (data[2].equals(connect))
                            {
                                // ta_chat.setText("");
                                userAdd(data[0]);
                            }
                            else if (data[2].equals(disconnect))
                            {
                                userRemove(data[0]);
                            }
                            else if (data[2].equals(done))
                            {
                                //usernametext.setText("");
                                writeUsers();
                                users.clear();
                            }
                            else if (data[2].equals("res"))
                            {
                                //usernametext.setText("");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        responseMethod(data[1]);
                                    }
                                });

                            }
                }
            }catch(Exception ex) {
                Log.d(TAG, "Exception:"+ex.getMessage());
            }
        }
    }

    //--------------------------//
    public void responseMethod(String msg)
    {
        if(msg.equals("first_user"))
        {
            setupFirstUser();
        }
        else {
            surahFragment.setSurahText(msg);
        }
    }
    public void setupFirstUser()
    {
        leftScroll.setVisibility(View.VISIBLE);
        rightScroll.setVisibility(View.VISIBLE);
        surahSecltionButton.setVisibility(View.VISIBLE);
    }
    public void sendChat(String text){
        String nothing = "";
        if ((text).equals(nothing)) {
            tf_chat.setText("");

        } else {
            try {
                writer.println(username + ":" + tf_chat.getText() + ":" + "Req");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.setText("Message was not sent. \n");
            }
            tf_chat.setText("");

        }

        tf_chat.setText("");

    }

//connection
    public void LogInClick(String username, final String address, final int port) {
        if (isConnected == false)
        {
            this.address=address;
           this.username = username;
             this.port=port;

            try
            {

                            new HttpRequestAsynTask_connect(
                                    getBaseContext(),"null",address,""+port
                            ).execute();
                            Thread.sleep(2000);




//                Log.d(TAG, "Addess:"+address+"Port:"+port);
//
//                InetAddress serverAddr = InetAddress.getByName(address);
//                sock=new Socket(serverAddr,port);
//                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
//                reader = new BufferedReader(streamreader);
//                writer = new PrintWriter(sock.getOutputStream());
//                writer.println(username + ":has connected.:Connect");
//                Log.d(TAG, username+":has connected.:Connect");
//                Toast.makeText(this,username+":has connected.:Connect",Toast.LENGTH_SHORT).show();
//                writer.flush();
//                isConnected = true;
//                connectBtn.setText("disconnect");

            }
            catch (Exception ex)
            {
                Log.d(TAG, "Error: Cannot Connect! Try Again."+ex.getMessage());
//                ta_chat.append("Cannot Connect! Try Again. \n");
//                tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true)
        {
            Log.d(TAG, "You are already connected. \\n");
            //ta_chat.append("You are already connected. \n");
        }

    }
//disconnect
    public void disconnectSocket() {
        sendDisconnect();
        Disconnect();
    }


    private class HttpRequestAsynTask_connect extends AsyncTask<Void, Void, Void>
    {
        private String ip_address,requestReplay,port_name;
        private Context context;
        private AlertDialog alartdialog;
        private String parameter;
        private String parametervalue;

        public HttpRequestAsynTask_connect(Context context, String parametervalue,
                                           String ip_address, String port_name) {
            // TODO Auto-generated constructor stub
            this.context=context;
            this.ip_address=ip_address;
            this.parametervalue=parametervalue;
            this.port_name=port_name;

        }
        //Request to connect with server
        public String sendRequest_openport(String ip_address,String port_number)
        {
            String serverResponse="ERROR";

            try{
//                socket = new Socket(ip_address,Integer.parseInt(port_number));
//                flag=1;
//
//                error=false;
//                serverResponse="ok";
//                Log.d("RCV","B4 RUN METHOD RUN");
//                new Thread(new Listen()).start();
                //   thread.start();
                Log.d(TAG, "Addess:"+address+"Port:"+port);

                InetAddress serverAddr = InetAddress.getByName(address);
                sock=new Socket(serverAddr,port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                Log.d(TAG, username+":has connected.:Connect");

                writer.flush();
                isConnected = true;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LogInActivity.this,username+":has connected.:Connect",Toast.LENGTH_SHORT).show();
                        connectBtn.setText("disconnect");
                    }
                });

                return serverResponse;
            }catch(Exception e)
            {
                //error=true;

                return serverResponse;
            }




        }
        public String sendRequest_close() throws UnknownHostException, IOException
        {

            String serverResponse="ERROR";


            try{
//                socket.close();
//                flag=0;
//                error=false;
//                Toast.makeText(getBaseContext(), "CONNECTED", Toast.LENGTH_LONG).show();
//                serverResponse="ok";
            }catch(Exception e)
            {

               // error=true;
            }



            return serverResponse;

        }
        public String sendRequest_wrData(String parameterValue,String ip_address) throws UnknownHostException, IOException
        {
//            Log.d("RCV2",msg);
            String serverResponse="ERROR";
//            try{
//                DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
//                DOS.writeUTF(parameterValue);
//
//
//                flag=2;
//                error=false;
//                serverResponse="ok";
//
//            }catch(Exception e)
//            {
//
//                error=true;
//                serverResponse="ERROR";
//                msg="Error: "+e;
//            }


            return serverResponse;

        }


        @Override
        protected Void doInBackground(Void... voids) {
            // TODO Auto-generated method stub
            try {
                sendRequest_openport(ip_address,port_name);
//                if(flag==0)
//                    requestReplay=sendRequest_close();
//                else if(flag==1){
//                    requestReplay=sendRequest_openport(ip_address,port_name);
//                    rep=requestReplay;
//
//                }
//                else if(flag==2)
//                    requestReplay=sendRequest_wrData(parametervalue,ip_address);

            } catch (Exception e) {
                // TODO Auto-generated catch block

                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecution(Void avoid)
        {
            //alartdialog.setMessage(requestReplay);
        }
        protected void onPreExecution() {
            //alartdialog.setMessage("Sending data to server... Pleasewait");

        }

    }


}
