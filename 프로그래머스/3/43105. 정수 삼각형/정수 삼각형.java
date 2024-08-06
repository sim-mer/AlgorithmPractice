class Solution {
    public int solution(int[][] triangle) {
        
        if (triangle.length == 1) {
            return triangle[0][0];
        }
        
        for(int i = 1; i < triangle.length; i++) {
            
            for(int j = 0; j < triangle[i].length; j++) {
                
                if(j == 0) {
                    triangle[i][j] += triangle[i - 1][j];
                    continue;
                }
                
                if(j == triangle[i].length - 1) {
                    triangle[i][j] += triangle[i - 1][j - 1];
                    continue;
                }
                
                int pre = compare(triangle[i - 1][j], triangle[i - 1][j - 1]);
                triangle[i][j] += pre;
                
            }
            
        }
        
        int last = triangle.length - 1;
        int max = 0;
        for(int i : triangle[last]) {
            if(i > max) max = i;
        }
        
        return max;
    }
    
    private int compare(int a, int b) {
        if(a > b) return a;
        if(b > a) return b;
        return a;
    }
}