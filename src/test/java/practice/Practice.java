package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Practice {

	// count array value counts
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         /* String []arr={"kuber", "kuber","kju","st","st"};
		
          HashMap <String, Integer> map= new HashMap<>();
          
          for(int i=0;i<arr.length;i++) {
        	if(map.containsKey(arr[i])){ 
		     int count= map.get(arr[i]);
		    map.put(arr[i], ++count);      
		    
		    }
		else {
			map.put(arr[i],1);
		}}
        	System.out.println(map);
		*/
	
		
/*		String sti="kuberjayswal";
        int s=3;
        ArrayList<String>al = new ArrayList<>();
   if((sti.length()%s)!=0) {
	System.out.println(" not divisable");
} else {
	System.out.println("Divisble");
    for(int i=s;i<=sti.length();i=i+s) {
	if(al.isEmpty()) {
	al.add(sti.substring(i,i+s));}
	else
		System.out.println(sti.substring(0,i));
	{al.add(sti.substring(0,i));}
}

   System.out.println(al); 	 
     
	}	*/
		
		String []arg= {"1234", "6004","123","6745"};
		
		List<String>l1= Arrays.asList(arg);
		int Count=0;
		for(int i=0;i<l1.size();i++){
			if(l1.get(i).length()==4) {
				
				/*if(String.valueOf(l1.get(i).charAt(0)).equals("6")) {*/
				if(l1.get(i).startsWith("6")) {
					Count=Count+1;
					
				}
			}
			
		}
		
		System.out.println(Count);
		
		
		
		
		
		/*if(String.valueOf(l1.get(i).charAt(0)).equals("6")) {*/
		if(l1.get(i).startsWith("6")) {
			Count=Count+1;
			
		}
	}
	
}

System.out.println(Count);		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}}
