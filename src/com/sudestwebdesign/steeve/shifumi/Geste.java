package com.sudestwebdesign.steeve.shifumi;

public enum Geste
{
	PAPIER
	{
		public String toString()
		{
			return "P";
		}
	},
	PIERRE
	{
		public String toString()
		{
			return "M";
		}
	},
	CISEAUX
	{
		public String toString()
		{
			return "C";
		}
	}
}
