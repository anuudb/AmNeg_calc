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
		int a =0;
		
		for(int i = 0; i < prc_diff.size()-1; i++){
			if(j == 7){ // Pre-mentioned condition that maximum range of a amNeg slop must be less than or equal to 7
				i = i - j;
				j=0;
				System.out.println("Limit exceed ...**************************************************");
				break;
			}
			else if(prc_diff.get(i)[0]<0){ // checking the value is a significant drop or not
				if(prc_diff.get(i+1)[0]<0){ // checking the next value is a significant drop or not
					double x = prc_diff.get(i)[0] + prc_diff.get(i+1)[0];
					double y = (prc_diff.get(i)[0] * 1.5);
					double z = (prc_diff.get(i+1)[0] * 1.5);
					if(((x<=y) && (x<=z)) || ((x<=y) && (x>z))){ // Condition
						j++;
						double[] result = new double[3];
						result[0] = amneg_slop_id;
						result[1] = prc_diff.get(i)[0];
						result[2] = prc_diff.get(i)[1];
						result_list.add(result);
						
						/*
						System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
						System.out.println("aaaaaaaa: "+a);*/
						a++;
						
						//System.out.println("length : "+prc_diff.size());
						if((i+2 == prc_diff.size())){
							double[] result1 = new double[3];
							result1[0] = amneg_slop_id;
							result1[1] = prc_diff.get(i+1)[0];
							result1[2] = prc_diff.get(i+1)[1];
							result_list.add(result1);
							
							/*System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
							System.out.println("aaaaaaaa: *************"+a);
							System.out.println("______________________________________");*/
							
							a++;
							amneg_slop_id++;
							j = 0;
						}
						else if(prc_diff.get(i+2)[0]>0 || ((2 * prc_diff.get(i+2)[0]) > prc_diff.get(i+1)[0])){
							double[] result2 = new double[3];
							result2[0] = amneg_slop_id;
							result2[1] = prc_diff.get(i+1)[0];
							result2[2] = prc_diff.get(i+1)[1];
							result_list.add(result2);
							
							/*System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
							System.out.println("aaaaaaaa: "+a);
							System.out.println("______________________________________");*/
							a++;
							amneg_slop_id++;
							j = 0;
							/*for(int w=0; w < a; w++){
								System.out.println("slop id: "+result_list.get(a)[0]+" val: "+result_list.get(a)[1]+" d_id: "+result_list.get(a)[2]);
							}*/
						}
						else{
							continue;
						}
					}
				}
				else{
					continue;
				}
			}
			else{
				continue;
			}
		}
		for(int w=0; w < a; w++){
			System.out.println("slop id: "+result_list.get(w)[0]+" val: "+result_list.get(w)[1]+" d_id: "+result_list.get(w)[2]);
			System.out.println("aaaaaaaaaaaaaa        :"+a);
			System.out.println("wwwwwwwwwwwwww        :"+w);
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
		
		return prc_diff;
	}
}
