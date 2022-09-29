class _2 {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dLast = n - 1, pLast = n - 1;
        
        while(dLast >= 0 && deliveries[dLast] == 0) {
        	dLast--;
        }
        while(pLast >= 0 && pickups[pLast] == 0) {
        	pLast--;
        }
        
        //수거가 더 많은 경우 배달할수있어도 수거만 하고 오는게 문제다.
        while(dLast>=0 || pLast>=0) {
        	answer += dLast > pLast ? (dLast+1)*2 : (pLast+1)*2; 

        	int dCnt = 0;
        	int i = dLast;
        	for(; i >= 0 ; i--) {
        		if(dCnt + deliveries[i] > cap) {
        			deliveries[i] += dCnt - cap;
        			break;
        		}
        		else
        			dCnt += deliveries[i];
        	}
        	dLast = i;
        	
        	int pCnt = 0;
        	i = pLast;
        	for(; i >= 0 ; i--) {
        		if(pCnt + pickups[i] > cap) {
        			pickups[i] += pCnt - cap;
        			break;
        		}
        		else
        			pCnt += pickups[i];
        	}
        	pLast = i;
        }
        return answer;
    }
}