import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Random;

public class song_recommender{
	public void A(int id,int x,int y) throws IOException{
		String cmd="./11.sh "+id;
		Process p=Runtime.getRuntime().exec(cmd);
		BufferedInputStream in = new BufferedInputStream(p.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String linestr=br.readLine();
		int birth = Integer.parseInt(linestr);
		ArrayList<Integer> user = new ArrayList<Integer>();
		cmd="./12.sh "+birth;
		p=Runtime.getRuntime().exec(cmd);
		in = new BufferedInputStream(p.getInputStream());
		br = new BufferedReader(new InputStreamReader(in));
		while((linestr=br.readLine())!=null && !linestr.equals("")){
			user.add(Integer.parseInt(linestr));
		}
		ArrayList<Integer> song = new ArrayList<Integer>();
		int type=0;
		if(x==y && x==1){
			type=1;
		}
		else if(x==1 && y>1){
			type=2;
		}
		else if(x==1 && y<1){
			type=3;
		}
		else if(x>1 && y==1){
			type=4;
		}
		else if(x<1 && y==1){
			type=5;
		}
		else if(x>1 && y>1){
			type=6;
		}
		else if(x<1 && y<1){
			type=7;
		}
		else if(x>1 && y<1){
			type=8;
		}
		else if(x<1 && y>1){
			type=9;
		}
		cmd="./13.sh "+type+" "+x+" "+y;
		p=Runtime.getRuntime().exec(cmd);
		in = new BufferedInputStream(p.getInputStream());
		br = new BufferedReader(new InputStreamReader(in));
		while((linestr=br.readLine())!=null && !linestr.equals("")){
			song.add(Integer.parseInt(linestr));
		}
		ArrayList<Integer> like=new ArrayList<Integer>();
		cmd="./14.sh "+id;
		p=Runtime.getRuntime().exec(cmd);
		in = new BufferedInputStream(p.getInputStream());
		br = new BufferedReader(new InputStreamReader(in));
		int signal=0,recom_song_id=0,check_song_id=-1;
		while((linestr=br.readLine())!=null && !linestr.equals("")){
			like.add(Integer.parseInt(linestr));
		}
		ArrayList<Integer> recom = new ArrayList<Integer>();
		Random ran=new Random();
		cmd="./15.sh "+id;
		p=Runtime.getRuntime().exec(cmd);
		in = new BufferedInputStream(p.getInputStream());
		br = new BufferedReader(new InputStreamReader(in));
		while((linestr=br.readLine())!=null && !linestr.equals("")){
			check_song_id=Integer.parseInt(linestr);
		}
		if(like.size()!=0){
			ArrayList<Integer> count_like_song = new ArrayList<Integer>();
			if(user.size()==1)
				signal=1;
			if(signal==0){
				for(int i=0;i!=user.size();i++){
					int reg_id=user.get(i);
					cmd="./14.sh "+reg_id;
					p=Runtime.getRuntime().exec(cmd);
					in = new BufferedInputStream(p.getInputStream());
					br = new BufferedReader(new InputStreamReader(in));
					while((linestr=br.readLine())!=null && !linestr.equals("")){
						count_like_song.add(Integer.parseInt(linestr));
					}
				}
				for(int i=0;i!=count_like_song.size();i++){
					int reg_so=count_like_song.get(i);
					for(int j=i+1;j!=count_like_song.size();j++){
						if(count_like_song.get(j)==reg_so){
							count_like_song.remove(i);
							i--;
							break;
						}
					}
				}
				int[][]array = new int[count_like_song.size()+1][user.size()+1];
				array[0][1]=id;
				int re=2;
				for(int i=0;i!=user.size();i++){
					if(user.get(i)!=id){
						array[0][re]=user.get(i);
						re++;
					}
				}
				for(int i=0;i!=count_like_song.size();i++){
					array[i+1][0]=count_like_song.get(i);
				}

				for(int i=0;i!=user.size();i++){
					ArrayList<Integer> al=new ArrayList<Integer>();
					cmd="./14.sh "+array[0][i+1];
					p=Runtime.getRuntime().exec(cmd);
					in = new BufferedInputStream(p.getInputStream());
					br = new BufferedReader(new InputStreamReader(in));
					while((linestr=br.readLine())!=null && !linestr.equals("")){
						al.add(Integer.parseInt(linestr));
					}
					while(al.size()!=0){
						for(int j=0;j!=count_like_song.size();j++){
							if(array[j+1][0]==al.get(0)){
								array[j+1][i+1]++;
								break;
							}
						}
						al.remove(0);
					}
			}
				double abs_Vs=0,abs_Vx=0,Vsx=0,reg_Vsx=0;
				int reg_j=0;
				for(int i=1;i!=count_like_song.size()+1;i++){
					abs_Vs=abs_Vs+(array[i][1]*array[i][1]);
				}
				abs_Vs=Math.sqrt(abs_Vs);
				for(int i=2;i!=user.size()+1;i++){
					for(int j=1;j!=count_like_song.size()+1;j++){
						abs_Vx=abs_Vx+(array[j][i]*array[j][i]);
						Vsx=Vsx+(array[j][1]*array[j][i]);
					}
					abs_Vx=Math.sqrt(abs_Vx);
					if(Vsx/abs_Vx/abs_Vs>reg_Vsx){
						reg_Vsx=Vsx/abs_Vx/abs_Vs;
						reg_j=i;
					}
					abs_Vx=0;
					Vsx=0;
				}
				if(reg_j!=0){
					ArrayList<Integer> whos = new ArrayList<Integer>();
					cmd="./14.sh "+reg_j;
					p=Runtime.getRuntime().exec(cmd);
					in=new BufferedInputStream(p.getInputStream());
					br=new BufferedReader(new InputStreamReader(in));
					while((linestr=br.readLine())!=null && !linestr.equals("")){
						whos.add(Integer.parseInt(linestr));
					}
					for(int i=0;i!=song.size();i++){
						for(int j=0;j!=whos.size();j++){
							if(whos.get(j)==song.get(i)){
								recom.add(whos.get(j));
							}
						}
					}
				}
				int[] total=new int[count_like_song.size()];
				for(int i=0;i!=count_like_song.size();i++){
					for(int j=1;j!=user.size()+1;j++){
						total[i]=total[i]+array[i+1][j];
					}
				}
				int max=0;
				for(int i=0;i!=count_like_song.size();i++){
					for(int j=0;j!=song.size();j++){
						if(array[i+1][0]==song.get(j) && total[i]>total[max]){
							max=i;
						}
					}
				}
				if(max==0){
					for(int i=0;i!=song.size();i++){
						if(total[max]==song.get(i)){
							recom.add(array[1][0]);
							break;
						}
					}
				}
				else{
					recom.add(array[max+1][0]);
				}
			}
			if(signal==1){
				for(int i=0;i!=like.size();i++){
					for(int j=0;j!=song.size();j++){
						if(song.get(j)==like.get(i)){
							recom.add(like.get(i));
							break;
						}
					}
				}
			}
			for(int k=0;k!=recom.size();k++){
				if(recom.get(k)!=check_song_id){
					recom_song_id=recom.get(k);
					break;
				}
			}
		}
		while(recom_song_id==0){
			recom_song_id=song.get(ran.nextInt(song.size()));
			if(recom_song_id==check_song_id)
				recom_song_id=0;
		}
		cmd="./16.sh "+id+" "+recom_song_id;
		p=Runtime.getRuntime().exec(cmd);
		in = new BufferedInputStream(p.getInputStream());
		br = new BufferedReader(new InputStreamReader(in));
		cmd="./17.sh "+recom_song_id;
		p=Runtime.getRuntime().exec(cmd);
		in = new BufferedInputStream(p.getInputStream());
		br = new BufferedReader(new InputStreamReader(in));
		linestr=br.readLine();
		System.out.println(linestr);
	}
	public void B(int id,String song_name) throws IOException{
		String cmd="./7.sh "+'"'+song_name+'"';
		Process p=Runtime.getRuntime().exec(cmd);
		BufferedInputStream in =new BufferedInputStream(p.getInputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String linestr=br.readLine();
		if (linestr.equals("")){
			System.out.println("not found");
		}
		else{
			System.out.println(linestr);
			cmd="./8.sh "+'"'+song_name+'"';
			p=Runtime.getRuntime().exec(cmd);
			in = new BufferedInputStream(p.getInputStream());
			br = new BufferedReader(new InputStreamReader(in));
			linestr = br.readLine();
			cmd="./9.sh "+id+" "+linestr;
			p=Runtime.getRuntime().exec(cmd);
		}
	}
	public void C(int id,int song_id) throws IOException{
		String cmd="./10.sh "+song_id+" "+id;
		Process p = Runtime.getRuntime().exec(cmd);
		System.out.println("Sucessful");
	}
}
