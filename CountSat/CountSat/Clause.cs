//
//  Clause.cs
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

	public struct Clause {

		int dat;

		public Clause (int i1, int v1, int i2, int v2, int i3, int v3) {
			dat = i1|(v1<<0x05)|(i2<<0x06)|(v2<<0x0b)|(i3<<0x0c)|(v3<<0x12);
		}

		public int I1 {
			get {
				return dat&0x1f;
			}
		}

		public int I2 {
			get {
				return (dat>>0x06)&0x1f;
			}
		}

		public int I3 {
			get {
				return (dat>>0x0c)&0x1f;
			}
		}

		public int V1 {
			get {
				return (dat>>0x05)&0x01;
			}
		}

		public int V2 {
			get {
				return (dat>>0x0b)&0x01;
			}
		}

		public int V3 {
			get {
				return (dat>>0x12)&0x01;
			}
		}

		public bool IsValid () {
			int x1 = dat&0x1f;
			int x2 = (dat>>0x06)&0x1f;
			int x3 = (dat>>0x0c)&0x1f;
			int v1 = (dat>>0x05)&0x01;
			int v2 = (dat>>0x0b)&0x01;
			int v3 = (dat>>0x12)&0x01;
			if(x1 == x2 && v1 != v2) {
				return false;
			}
			if(x1 == x3 && v1 != v3) {
				return false;
			}
			if(x2 == x3 && v2 != v3) {
				return false;
			}
			return true;
		}

		public int Match (int x) {
			int x1 = x>>(dat&0x1f);
			int x2 = x>>((dat>>0x06)&0x1f);
			int x3 = x>>((dat>>0x0c)&0x1f);
			int v1 = dat>>0x05;
			int v2 = dat>>0x0b;
			int v3 = dat>>0x12;
			return (~((x1^v1)&(x2^v2)&(x3^v3)))&0x01;
		}

		public override string ToString () {
			return string.Format ("[Clause: I1={0}, I2={1}, I3={2}, V1={3}, V2={4}, V3={5}]", I1, I2, I3, V1, V2, V3);
		}

	}
}

