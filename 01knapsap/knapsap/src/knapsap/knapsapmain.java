package knapsap;

public class knapsapmain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		knapsap_solution[] backPack = new knapsap_solution[3];
		
		backPack[0] = new brutal();
		backPack[1] = new stepping_stone();
		backPack[2] = new functional();
		
		for(int i = 0; i < 2; i++)
		{
			backPack[i].solution();
			backPack[i].WhatStuff();
		}
		
		((functional) backPack[2]).solution(1);
		((functional) backPack[2]).graph();
	}
}
