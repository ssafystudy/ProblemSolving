class 신규아이디추천 {
    public String solution(String new_id) {
    	new_id = new_id.toLowerCase();
    	new_id = new_id.replaceAll("[^a-z0-9_.-]", "");
    	new_id = new_id.replaceAll("[.]{2,}", ".");
    	new_id = new_id.replaceAll("^[.]|[.]$", "");
    	
    	if(new_id.equals(""))
    		return "aaa";
    	
    	if(new_id.length() >= 16) {
    		if(new_id.charAt(14) == '.')
    			new_id = new_id.substring(0, 14);
    		else
    			new_id = new_id.substring(0, 15);
    	}
    	else if(new_id.length() == 2) {
    		new_id += new_id.charAt(1);
    	}
    	else if(new_id.length() == 1) {
    		new_id += new_id.charAt(0);
    		new_id += new_id.charAt(0);
    	}
    	
    	return new_id;
    }
}