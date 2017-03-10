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
						System.out.println("result :"+prc_diff.get(i)[0]+" : "+prc_diff.get(i)[1]);
						//System.out.println("length : "+prc_diff.size());
						if((i+2 == prc_diff.size())){
							System.out.println("Slop end: "+prc_diff.get(i+1)[0]+" : "+prc_diff.get(i+1)[1]);
							// Last value of the amNeg
							System.out.println("Slop id: "+amneg_slop_id);
							amneg_slop_id++;
							j = 0;
						}
						else if(prc_diff.get(i+2)[0]>0 || ((2 * prc_diff.get(i+2)[0]) > prc_diff.get(i+1)[0])){
							System.out.println("Last val: " +prc_diff.get(i+1)[0]+ " : " +prc_diff.get(i+1)[1]);
							// Last value of amNeg slop
							System.out.println("Slop id: "+amneg_slop_id);
							amneg_slop_id++;
							j = 0;
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
