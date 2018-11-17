import java.io.*;
import java.net.*;
 class Server {
    static Socket s;
    private static DataOutputStream dout;
    Server(){
        try{
            //ServerSocket ss=new ServerSocket(6667);
            System.out.println("1");
            Socket s=new Socket("localhost",6667);
            //s=ss.accept();//establishes connection
            dout=new DataOutputStream(s.getOutputStream());
            System.out.println("2");
            DataInputStream din=new DataInputStream(s.getInputStream());
            int  str=4;

            while(str!=5){
                System.out.println("3");

                    str=din.readInt();
                    if(str==100)
                    {
                        Frame.start=true;
                        Frame.game=Frame.RUNNING;
                    }

                    if(str!=100) {
                        if(str<17) {
                            Body.move = str/4;
                        }
                        if(str>19) {
                            Body2.move2 = str/20;
                        }
                    }
                    System.out.println("read");
            }
            din.close();
            s.close();
            //ss.close();
        }catch(Exception e){System.out.println(e+"server");}
    }

    static void send(int key)
    {
        try{
            dout.writeInt(key);
            dout.flush();
        }catch(Exception e){System.out.println(e+"client");}
    }
}