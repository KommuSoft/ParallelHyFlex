//
//  Main.cs
//
//  Author:
//       Willem Van Onsem <vanonsem.willem@gmail.com>
//
//  Copyright (c) 2013 Willem Van Onsem
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
using System;

namespace CountSat {

	public class MainClass {

		public MainClass ()
		{
		}

		public static void Main (String[] args)
		{
			int N = int.Parse(args[0]);
			int n = int.Parse(args[1]);//max 32
			int k = int.Parse(args[2]);
			int kh = int.Parse(args[3]);
			Clause[] clauses = new Clause[k];
			int[] val = new int[k - kh + 2];
			int[,] totDat = new int[N,k - kh + 2];
			Random r = new Random ();
			for (int sample = 0; sample < N; sample++) {
				for (int i = 0; i < k;) {
					Clause c = new Clause (r.Next (n), r.Next (2), r.Next (n), r.Next (2), r.Next (n), r.Next (2));
					if (c.IsValid ()) {
						clauses [i++] = c;
						//Console.WriteLine(c);
					}
				}
				int cv = 0, j;
				for (int x = (1<<n)-1; x >= 0; x--) {
					cv = 1;
					for (j = 0; j < kh; j++) {
						cv &= clauses [j].Match (x);
					}
					if (cv != 0) {
						for (; j < k; j++) {
							cv += clauses [j].Match (x);
						}
					}
					val [cv]++;
				}
				Console.Write("#");
				for (int i = 0; i < k-kh+2; i++) {
					Console.Write (val [i]);
					Console.Write ("\t");
					totDat[sample,i] = val[i];
					val [i] = 0;
				}
				Console.WriteLine ();
			}
			Console.WriteLine ();
			Console.WriteLine ("#summary");
			int[] aftercache = new int[N];
			for (int i = 0; i < k-kh+2; i++) {
				for(int j = 0; j < N; j++) {
					aftercache[j] = totDat[j,i];
				}
				Array.Sort(aftercache);
				Console.Write((i-1));
				for(int j = 0; j <= 4; j++) {
					int l = Math.Min(N-1,j*N/4);
					Console.Write("\t"+aftercache[l]);
				}
				Console.WriteLine();
			}
		}
	}
}

