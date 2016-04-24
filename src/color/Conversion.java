package color;

/**
 * Created by jordan on 4/14/16.
 */
public class Conversion
{

	public static String LABtoRGB(double L, double A, double B)
	{
		// first convert LAB to XYZ
		L = ( L + 16 )/116;
		A = ( A / 500 ) + L;
		B = L - ( B / 200 );

		if ( Math.pow( L, 3 ) > 0.008856 )
		{
			L = Math.pow( L, 3 );
		}
		else
		{
			L = ( L - 16 / 116 ) / 7.787;
		}
		if ( Math.pow( A, 3 ) > 0.008856 )
		{
			A = Math.pow( A, 3 );
		}
		else
		{
			A = ( A - 16 / 116 ) / 7.787;
		}
		if ( Math.pow( B, 3 ) > 0.008856 )
		{
			B = Math.pow( B, 3 );
		}
		else
		{
			B = ( B - 16 / 116 ) / 7.787;
		}

		double X = L * 95.047;
		double Y = A * 100.000;
		double Z = B * 108.883;

		// Now convert XYZ to RGB
		X = X / 100;
		Y = Y / 100;
		Z = Z / 100;

		double R = ( X * 3.2406 ) + ( Y * -1.5372 ) + ( Z * -0.4986 );
		double G = ( X * -0.9689 ) + ( Y *  1.8758 ) + ( Z *  0.0415 );
		B = ( X *  0.0557 ) + ( Y * -0.2040 ) + ( Z *  1.0570 );

		if ( R > 0.0031308 )
		{
			R = 1.055 * ( Math.pow( R, ( 1 / 2.4 ) ) ) - 0.055;
		}
		else
		{
			R = 12.92 * R;
		}
		if ( G > 0.0031308 )
		{
			G = 1.055 * ( Math.pow( G, ( 1 / 2.4 ) ) ) - 0.055;
		}
		else
		{
			G = 12.92 * G;
		}
		if ( B > 0.0031308 )
		{
			B = 1.055 * ( Math.pow( B, ( 1 / 2.4 ) ) ) - 0.055;
		}
		else
		{
			B = 12.92 * B;
		}

		// Return the RGB values as a single hex string
		String RGB = Long.toHexString(Double.doubleToRawLongBits(R)) +
				Long.toHexString(Double.doubleToRawLongBits(G)) +
				Long.toHexString(Double.doubleToRawLongBits(B));

		return RGB;
	}

	public static double[] LABtoRGBArray(double L, double A, double B)
	{
		// first convert LAB to XYZ
		L = ( L + 16 )/116;
		A = ( A / 500 ) + L;
		B = L - ( B / 200 );

		if ( Math.pow( L, 3 ) > 0.008856 )
		{
			L = Math.pow( L, 3 );
		}
		else
		{
			L = ( L - 16 / 116 ) / 7.787;
		}
		if ( Math.pow( A, 3 ) > 0.008856 )
		{
			A = Math.pow( A, 3 );
		}
		else
		{
			A = ( A - 16 / 116 ) / 7.787;
		}
		if ( Math.pow( B, 3 ) > 0.008856 )
		{
			B = Math.pow( B, 3 );
		}
		else
		{
			B = ( B - 16 / 116 ) / 7.787;
		}

		double X = L * 95.047;
		double Y = A * 100.000;
		double Z = B * 108.883;

		// Now convert XYZ to RGB
		X = X / 100;
		Y = Y / 100;
		Z = Z / 100;

		double R = ( X * 3.2406 ) + ( Y * -1.5372 ) + ( Z * -0.4986 );
		double G = ( X * -0.9689 ) + ( Y *  1.8758 ) + ( Z *  0.0415 );
		B = ( X *  0.0557 ) + ( Y * -0.2040 ) + ( Z *  1.0570 );

		if ( R > 0.0031308 )
		{
			R = 1.055 * ( Math.pow( R, ( 1 / 2.4 ) ) ) - 0.055;
		}
		else
		{
			R = 12.92 * R;
		}
		if ( G > 0.0031308 )
		{
			G = 1.055 * ( Math.pow( G, ( 1 / 2.4 ) ) ) - 0.055;
		}
		else
		{
			G = 12.92 * G;
		}
		if ( B > 0.0031308 )
		{
			B = 1.055 * ( Math.pow( B, ( 1 / 2.4 ) ) ) - 0.055;
		}
		else
		{
			B = 12.92 * B;
		}

		return new double[] {R, G, B};
	}

}
