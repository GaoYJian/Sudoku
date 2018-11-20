package com.example.sudoku;

import java.io.InputStream;

public class basic {
	private int[][] su;
	private boolean[][] suT;
	private int active[];

	public basic() {
		su = new int[9][9];
		suT = new boolean[9][9];
		active = new int[2];
		// this.read(2);
	}

	public int[][] getSu() {
		return su;
	}

	public boolean[][] getSuT() {
		return suT;
	}

	public void setSu(int[][] su) {
		this.su = su;
	}

	public void setSu(int i, int j, int n) {
		su[i][j] = n;
	}

	public boolean read(InputStream in, int n) {
		try {
			byte[] b = new byte[163];
			for (int i = 0; i < n; i++) {
				in.read(b);
			}
			if(b[0]==0)
			{
				return false;
			}
			int s = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					su[i][j] = b[s]-'0';
					s+=2;
					if (su[i][j] != 0) {
						suT[i][j] = true;
					}else{
						suT[i][j] = false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		/*
		 * File file = new File("read.txt"); if (!file.exists()) { return false;
		 * } else { try { Scanner input = new Scanner(file); for (int i = 0; i <
		 * n; i++) { input.nextLine(); } for (int i = 0; i < 9; i++) { for (int
		 * j = 0; j < 9; j++) { su[i][j] = input.nextInt(); if (su[i][j] != 0) {
		 * suT[i][j] = true; } } } return true; } catch (FileNotFoundException
		 * ex) { Logger.getLogger(basic.class.getName()).log(Level.SEVERE, null,
		 * ex); return false; } }
		 */
	}

	public void setActive(int[] active) {
		this.active = active;
	}

	public int[] getActive() {
		return active;
	}

	public boolean checkSudoku() {
		int i = su[active[0]][active[1]];
		int j = 0;
		while (j < 9) {// 判列重复
			if (su[j][active[1]] == i && j != active[0]) {
				return false;
			}
			j++;
		}
		j = 0;
		while (j < 9) {// 判行重复
			if (su[active[0]][j] == i && j != active[1]) {
				return false;
			}
			j++;
		}
		if (!checkGrid(active[0], active[1])) {// 判方格重复
			return false;
		}
		return true;
	}

	public boolean checkSudoku(int i, int j, int k) {
		int count = 0;
		while (count < 9) {// 判列重复
			if (su[count][j] == k && count != i) {
				return false;
			}
			count++;
		}
		count = 0;
		while (count < 9) {// 判行重复
			if (su[i][count] == k && count != j) {
				return false;
			}
			count++;
		}
		if (!checkGrid(i, j, k)) {
			return false;
		}
		return true;
	}

	public boolean checkGrid(int i, int j) {// 判方格重复
		int[] row = new int[2];
		int[] line = new int[2];
		if (i % 3 == 0) {
			row[0] = 1;
			row[1] = 2;
		} else if (i % 3 == 1) {
			row[0] = -1;
			row[1] = 1;
		} else {
			row[0] = -2;
			row[1] = -1;
		}
		if (j % 3 == 0) {
			line[0] = 1;
			line[1] = 2;
		} else if (j % 3 == 1) {
			line[0] = -1;
			line[1] = 1;
		} else {
			line[0] = -2;
			line[1] = -1;
		}
		if (su[i][j] == su[i + row[0]][j]) {
			return false;
		} else if (su[i][j] == su[i + row[1]][j]) {
			return false;
		} else if (su[i][j] == su[i][j + line[0]]) {
			return false;
		} else if (su[i][j] == su[i][j + line[1]]) {
			return false;
		} else if (su[i][j] == su[i + row[0]][j + line[0]]) {
			return false;
		} else if (su[i][j] == su[i + row[0]][j + line[1]]) {
			return false;
		} else if (su[i][j] == su[i + row[1]][j + line[0]]) {
			return false;
		} else if (su[i][j] == su[i + row[1]][j + line[1]]) {
			return false;
		}
		return true;
	}

	public boolean checkGrid(int i, int j, int k) {
		int[] row = new int[2];
		int[] line = new int[2];
		if (i % 3 == 0) {
			row[0] = 1;
			row[1] = 2;
		} else if (i % 3 == 1) {
			row[0] = -1;
			row[1] = 1;
		} else {
			row[0] = -2;
			row[1] = -1;
		}
		if (j % 3 == 0) {
			line[0] = 1;
			line[1] = 2;
		} else if (j % 3 == 1) {
			line[0] = -1;
			line[1] = 1;
		} else {
			line[0] = -2;
			line[1] = -1;
		}
		if (k == su[i + row[0]][j]) {
			return false;
		} else if (k == su[i + row[1]][j]) {
			return false;
		} else if (k == su[i][j + line[0]]) {
			return false;
		} else if (k == su[i][j + line[1]]) {
			return false;
		} else if (k == su[i + row[0]][j + line[0]]) {
			return false;
		} else if (k == su[i + row[0]][j + line[1]]) {
			return false;
		} else if (k == su[i + row[1]][j + line[0]]) {
			return false;
		} else if (k == su[i + row[1]][j + line[1]]) {
			return false;
		}
		return true;
	}

	public boolean solution(int i, int j) {
		if (i > 8 || j > 8) {
			return true;
		}
		if (suT[i][j] == true) {
			if (j < 8) {
				if (solution(i, j + 1)) {
					return true;
				}
			} else {
				if (i < 8) {
					if (solution(i + 1, 0)) {
						return true;
					}
				} else {
					return true;
				}
			}
			return false;
		} else {
			for (int k = 1; k <= 9; k++) {
				if (checkSudoku(i, j, k)) {
					su[i][j] = k;
					if (j < 8) {
						if (solution(i, j + 1)) {
							return true;
						}
					} else {
						if (i < 8) {
							if (solution(i + 1, 0)) {
								return true;
							}
						} else {
							return true;
						}
					}
					su[i][j] = 0;
				}
			}
			return false;
		}
	}

    public boolean checkOver(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(su[i][j]==0){
                    return false;
                }
            }
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(!checkSudoku(i,j,su[i][j])){
                    return false;
                }
            }
        }
        return true;
    }
    
    public void retSu()
    {
    	for(int i = 0 ; i < 9 ; i++){
    		for(int j = 0 ; j < 9 ; j++){
    			if(!suT[i][j]){
    				su[i][j] = 0;
    			}
    		}
    	}
    }

    public boolean checkRowO(int row){
    	for(int i = 0 ; i < 9 ; i++){
    		if(su[row][i] == 0){
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean checkColO(int col){
    	for(int i = 0; i < 9 ; i++){
    		if(su[i][col] == 0){
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean checkGridO(int row,int col){
    	int[] ca = new int[3];
    	int[] ra = new int[3];
    	switch(col%3){
    	case 0:ca[0]=0;ca[1]=1;ca[2]=2;break;
    	case 1:ca[0]=-1;ca[1]=0;ca[2]=1;break;
    	case 2:ca[0]=-2;ca[1]=-1;ca[2]=0;break;
    	}
    	switch(row%3){
    	case 0:ra[0]=0;ra[1]=1;ra[2]=2;break;
    	case 1:ra[0]=-1;ra[1]=0;ra[2]=1;break;
    	case 2:ra[0]=-2;ra[1]=-1;ra[2]=0;break;
    	}
    	for(int i = 0 ; i < 3 ; i++){
    		for(int j = 0 ; j < 3 ; j++){
    			if(su[row+ra[i]][col+ca[j]] == 0){
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
