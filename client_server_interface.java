public class client_server_interface{
	public static void main(String[] args){
		user_control user=new user_control();
		song_recommender song=new song_recommender();

		if(args[0].equals("1")){
			try{
				user.A(args[1],args[2],args[3],Integer.parseInt(args[4]));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(args[0].equals("2")){
			try{
				user.B(args[1],args[2]);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(args[0].equals("3")){
			try{
				song.A(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(args[0].equals("4")){
			try{
				song.B(Integer.parseInt(args[1]),args[2]);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else if(args[0].equals("5")){
			try{
				song.C(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		else
			System.out.println("Wrong input");
	}
}
