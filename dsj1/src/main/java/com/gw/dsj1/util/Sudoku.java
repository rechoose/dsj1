package com.gw.dsj1.util;


import java.util.ArrayList;
import java.util.HashMap;

public class Sudoku {

	private final int CROSS = 9;
	private final int ROW = 9;
	private final String SPLITTR = "_";

	private int[][] origin;

	private Chunk[] chunks = new Chunk[9];
	private HashMap<Integer, HashMap<Integer, Integer>> hasBeFillMapXY;//数字 2所填充的x，y
	private HashMap<Integer, HashMap<Integer, Integer>> hasBeFillMapYX;//数字 2所填充的y，x

	private HashMap<Integer, HashMap<Integer, Integer>> maybeIn;//数字 2所填充的y，x

	private boolean changed = false;

	private int blankCount = CROSS * ROW;

	public static void main(String[] args) {
//		String s = "123456789123456789123456789123456789123456789123456789123456789123456789123456789";
//		String s = "040020567000000930073091004367009000805046000200000690029050700756310400038000000";
		String s = "300000607007010009000800030020530001080602000059100000090000012000050900800090070";
		Sudoku sudoku = new Sudoku(s);
		sudoku.print();
		sudoku.doSu();

	}

	public Sudoku(int[][] origin) {
		this.origin = origin;
		init();
	}

	public Sudoku(String originStr) {
		int length = originStr.length();
		if (length != CROSS * ROW) {
			throw new IllegalArgumentException("长度异常");
		}
		this.origin = new int[CROSS][ROW];
		for (int i = 0; i < length; i++) {
			this.origin[i / 9][i % 9] = Integer.valueOf(String.valueOf(originStr.charAt(i)));
		}
		init();
	}

	public void doSu() {
		while (blankCount > 0) {
			do {
				changed = false;
				for (int i = 1; i < 10; i++) {
					findNextByDigit(i);
				}

			} while (changed);
		}

	}


	private void init() {
		hasBeFillMapXY = new HashMap<>();
		hasBeFillMapYX = new HashMap<>();
		for (int i = 0; i < CROSS; i++) {
			for (int j = 0; j < ROW; j++) {
				int tempDigit = origin[i][j];
				if (tempDigit > 9 || tempDigit < 1) {
					origin[i][j] = 0;
				} else {
					blankCount--;
					HashMap<Integer, Integer> fillXY = findhasBeFillByDigitXY(tempDigit);
					fillXY.put(i, j);
					HashMap<Integer, Integer> fillYX = findhasBeFillByDigitYX(tempDigit);
					fillYX.put(j, i);
				}
			}
		}
		//初始化chunk
		for (int i = 0; i < 9; i++) {
			chunks[i] = new Chunk(i);
		}

	}

	private HashMap<Integer, Integer> findhasBeFillByDigitXY(int digit) {
		return hasBeFillMapXY.computeIfAbsent(digit, k -> new HashMap<>());
	}

	private HashMap<Integer, Integer> findhasBeFillByDigitYX(int digit) {
		return hasBeFillMapYX.computeIfAbsent(digit, k -> new HashMap<>());
	}

	//每个数字
	private void findNextByDigit(int digit) {
		//便利每个宫
		for (int i = 0; i < chunks.length; i++) {
			Chunk chunk = chunks[i];
			Point contain = chunk.contain(digit);
			if (contain == null) {//不存在
				ArrayList<Integer> xs = new ArrayList<>();
				ArrayList<Integer> ys = new ArrayList<>();
				//看看该数字经过了该宫的哪行哪列
				for (int j = chunk.getX0(); j < chunk.getX1(); j++) {
					HashMap<Integer, Integer> filledXy = findhasBeFillByDigitXY(digit);
					Integer y = filledXy.get(j);
					if (y == null) {
						for (int k = chunk.getY0(); k < chunk.getY1(); k++) {
							HashMap<Integer, Integer> filledYx = findhasBeFillByDigitYX(digit);
							Integer x = filledYx.get(k);
							if (x == null) {
								//都没经过
								int beFillDigit = origin[j][k];
								if (beFillDigit < 1 || beFillDigit > 9) {
									//可以被填充
									xs.add(j);
									ys.add(k);
								}
							}
						}
					}
				}
				if (xs.size() == 1) {
					fill(digit, xs.get(0), ys.get(0));
				} else {
					StringBuilder sb = new StringBuilder();
					for (int j = 0; j < xs.size(); j++) {
						sb.append("(").append(xs.get(j)).append(",").append(ys.get(j)).append("),");
					}
					sb.deleteCharAt(sb.length() - 1);
					System.out.println("在宫格" + i + "中，数字" + digit + "可能存在" + xs.size() + "个位置: " + sb.toString());
				}
			}
		}
	}

	private void fill(int digit, int x, int y) {
		origin[x][y] = digit;
		changed = true;
		blankCount--;
		HashMap<Integer, Integer> fillxy = findhasBeFillByDigitXY(digit);
		fillxy.put(x, y);
		HashMap<Integer, Integer> fillyx = findhasBeFillByDigitYX(digit);
		fillyx.put(y, x);
		System.out.println("新填充" + digit + ": (" + x + "," + y + ")");
		print();
	}

	private void print() {
		for (int i = 0; i < 24; i++) {
			if (i > 4 && i % 2 != 0 && i < 23) {
				System.out.print((i - 4) / 2);
			} else {
				System.out.print("=");
			}
		}
		System.out.println();

		for (int i = 0; i < CROSS; i++) {
			for (int j = 0; j < ROW; j++) {
				if (j == 0) {
					System.out.print("(" + i + ")= ");
				}
				if (origin[i][j] < 1) {
					System.out.print(" ");
				} else {
					System.out.print(origin[i][j]);
				}
				System.out.print(" ");
				if (j == ROW - 1) {
					System.out.println("=");
				}
			}
		}

		for (int i = 0; i < 24; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	class Chunk {
		private int x0;
		private int x1;
		private int y0;
		private int y1;

		public Chunk(int x0, int x1, int y0, int y1) {
			this.x0 = x0;
			this.x1 = x1;
			this.y0 = y0;
			this.y1 = y1;
		}

		public Chunk(int num) {
			this.x0 = (num / 3) * 3;
			this.x1 = (num / 3) * 3 + 3;
			this.y0 = (num % 3) * 3;
			this.y1 = (num % 3) * 3 + 3;
		}

		public Point contain(int digit) {
			for (int i = x0; i < x1; i++) {
				for (int j = y0; j < y1; j++) {
					if (origin[i][j] == digit) {
						return new Point(i, j, digit);
					}
				}
			}
			return null;
		}

		public int getX0() {
			return x0;
		}

		public void setX0(int x0) {
			this.x0 = x0;
		}

		public int getX1() {
			return x1;
		}

		public void setX1(int x1) {
			this.x1 = x1;
		}

		public int getY0() {
			return y0;
		}

		public void setY0(int y0) {
			this.y0 = y0;
		}

		public int getY1() {
			return y1;
		}

		public void setY1(int y1) {
			this.y1 = y1;
		}
	}

	class Point {
		private int x;
		private int y;
		private int digit;

		public Point(int x, int y, int digit) {
			this.x = x;
			this.y = y;
			this.digit = digit;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getDigit() {
			return digit;
		}
	}

	class Row {

		private int crossNum;

		public Point contain(int digit) {
			for (int i = 0; i < ROW; i++) {
				if (origin[crossNum][i] == digit) {
					return new Point(crossNum, i, digit);
				}
			}
			return null;
		}
	}


}
