package pseudoPrice;

import java.util.ArrayList;

public class amneg_logic {

	public ArrayList<double[]> amneg_find( ArrayList<double[]> prc_diff) {
		// add logic here to calculate the amneg 
		//System.out.println("length : "+prc_diff.size());
		//System.out.println("gooot a job :"+prc_diff.get(1)[0]+" : "+prc_diff.get(1)[1]);
		
		int j = 0;
		int amneg_slop_id = 1;
		
		ArrayList<Double> prc_concecative_neg = new ArrayList<Double>();
		ArrayList<double[]> result_list = new ArrayList<double[]>() ;
		
		for(int i = 0; i < prc_diff.size()-1; i++){
			if(prc_diff.get(i)[0]<0){ // checking the value is a significant drop or not
				if(prc_diff.get(i+1)[0]<0){ // checking the next value is a significant drop or not
					double[] result = new double[3];
					result[0] = amneg_slop_id;
					result[1] = prc_diff.get(i)[0];
					result[2] = prc_diff.get(i)[1];
					
						//System.out.println("0 : "+result[0]+" "+result[1]+" "+result[2]);
					
					
					result_list.add(result);
					j++;
					//System.out.println("length : "+prc_diff.size());
					if(j==7){
						/*double[] resultnew = new double[3];
						resultnew[0] = 0;
						resultnew[1] = 0;
						resultnew[2] = 0;
						result_list.add(resultnew);*/
						amneg_slop_id++;
						j=0;
					}
					else if((i+2 == prc_diff.size())){
						double[] result11 = new double[3];
						result11[0] = amneg_slop_id;
						result11[1] = prc_diff.get(i+1)[0];
						result11[2] = prc_diff.get(i+1)[1];
						
							//System.out.println("1st : "+result[0]+" "+result[1]+" "+result[2]);
						
						result_list.add(result11);
						
//						System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
//						System.out.println("______________________________________");
						amneg_slop_id++;
						j = 0;
						break;
					}
					else if(prc_diff.get(i+2)[0]>=0 ){
						double[] result111 = new double[3];
						result111[0] = amneg_slop_id;
						result111[1] = prc_diff.get(i+1)[0];
						result111[2] = prc_diff.get(i+1)[1];
						result_list.add(result111);
						
							//System.out.println("2nd : "+result[0]+" "+result[1]+" "+result[2]);
						
						
//						System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
//						System.out.println("______________________________________");
						amneg_slop_id++;
						j = 0;
						
					}
					else{
						continue;
					}
				}
				else{
					j = 0;
					continue;
				}
			}
			else{
				j = 0;
				continue;
			}
			/*for(int w=0; w <= a; w++){
				System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
			}*/
		}
		
		/*for (int i = 0; i < prc_diff.size(); i++) {
			if(prc_diff.get(i)[0]<0){
				prc_concecative_neg.add(prc_diff.get(i)[0]);
				for (int j = i+1; j < j+8; j++) {
					if(j<=i){
						double a1_a2 = prc_diff.get(j-1)[0]+prc_diff.get(j)[0];
						if(a1)
					}
				}
			}
		}*/
		//System.out.println("anushka \n"+prc_diff);
		
		/*for (int k = 0; k < result_list.size(); k++) {
			System.out.println("h : "+result_list.get(k)[0]+" "+result_list.get(k)[1]+" "+result_list.get(k)[2]);
		}*/
		
		return result_list;
	}
}
