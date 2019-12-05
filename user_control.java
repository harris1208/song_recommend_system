import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;

public class user_control{
	public void A(String username,String pw,String gender,int birth) throws IOException{
		String cmd="./1.sh "+ '"' + username + '"';
		Process p =Runtime.getRuntime().exec(cmd);
		String result = null;
		BufferedInputStream in = new BufferedInputStream(p.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String linestr=br.readLine();
		if(linestr.equals("")){
			cmd="./2.sh";
			p =Runtime.getRuntime().exec(cmd);
			in = new BufferedInputStream(p.getInputStream());
			br = new BufferedReader(new InputStreamReader(in));
			linestr=br.readLine();
			int num=Integer.parseInt(linestr);
			num++;
			cmd="./3.sh "+num+" "+'"'+username+'"'+" "+'"'+pw+'"'+" '"+gender+"' "+birth;
			p = Runtime.getRuntime().exec(cmd);
			cmd="./4.sh "+num;
			p =Runtime.getRuntime().exec(cmd);
			System.out.println(num);
		}
		else{
			System.out.println("already");
		}
		br.close();
		in.close();
	}

	public void B(String username,String pw) throws IOException{
		String cmd="./5.sh "+'"'+username+'"';
		Process p =Runtime.getRuntime().exec(cmd);
		String result=null;
		BufferedInputStream in = new BufferedInputStream(p.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String linestr=br.readLine();
		if(linestr.equals("")){
			System.out.println("Wrong username");
		}
		else{
			cmd="./6.sh "+'"'+username+'"'+" "+'"'+pw+'"';
			p=Runtime.getRuntime().exec(cmd);
			in = new BufferedInputStream(p.getInputStream());
			br = new BufferedReader(new InputStreamReader(in));
			linestr=br.readLine();
			if(linestr.equals("")){
				System.out.println("Wrong password");
			}
			else{
				System.out.println("login successful:"+linestr);
			}
		}
		br.close();
		in.close();
	}
}
