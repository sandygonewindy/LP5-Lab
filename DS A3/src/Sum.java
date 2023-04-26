import java.util.*;

import mpi.MPI;


public class Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MPI.Init(args);
        int n = 6;
        int sum = 0;
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int per_process = n / size; 
        
        int [] send_buffer = {1, 2, 3, 4, 5, 6};
         
        
        int [] receive_buffer = new int[per_process];
        int [] partial_sum = new int[1];
        int [] ans = new int[3];

        int root = 0;
        
        MPI.COMM_WORLD.Scatter(send_buffer, 0, per_process, MPI.INT, receive_buffer, 0, per_process, MPI.INT, root);	
        
        partial_sum[0] = 0;
        for(int i = 0; i < per_process; i++) {
//        	current_sum += receive_buffer[i];
        	partial_sum[0] += receive_buffer[i];
        }
//        System.out.println("Process " + me + " has data: " + Arrays.toString(receive_buffer));
        System.out.println("Process " + me + " has data: " + Arrays.toString(receive_buffer) + " And has partial sum = " + partial_sum[0] + "\n");
//        System.out.println("----------------------------------------------");
//        current_sum  = 0;
        MPI.COMM_WORLD.Gather(partial_sum, 0, 1, MPI.INT, ans, 0, 1, MPI.INT, root);
//        System.out.println("Hello world from <"+me+"> of <"+size+">");
        if(me == 0) {
        	for(int i = 0; i < size; i++) {
        		sum += ans[i];
        	}
        	System.out.println("Total Sum = " + sum);
        }
        MPI.Finalize();   
	}

}
