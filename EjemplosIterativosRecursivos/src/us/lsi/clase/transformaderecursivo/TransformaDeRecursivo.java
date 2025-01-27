package us.lsi.clase.transformaderecursivo;

import java.util.stream.Stream;

public class TransformaDeRecursivo {
	
	public static String f(Integer a, Integer b, Integer c, String d) {
		String res;
		if (a < 3 || b < 3) {
			res = c.toString();
		} else if (a < 3) {
			res = "~" + f(a - 1, b - 1, c + 4, d);
		} else if (b < 3) {
			res = "-" + c + "-" + f(a - 2, b - 1, c, d + "x");
		} else {
			res = "/" + d + "/" + f(a - 1, b - 2, c + 1, d);
		}
		return res;
	}

	public static String fRF(Integer a, Integer b, Integer c, String d) {
        return fRF(a, b, c, d, "");
    }

    private static String fRF(Integer a, Integer b, Integer c, String d, String acc) {
        if (a < 3 || b < 3) {
            return acc + c.toString();
        } else if (a < 3) {
            return fRF(a - 1, b - 1, c + 4, d, acc + "~");
        } else if (b < 3) {
            return fRF(a - 2, b - 1, c, d + "x", acc + "-" + c + "-");
        } else {
            return fRF(a - 1, b - 2, c + 1, d, acc + "/" + d + "/");
        }
    }

    public static String fI(Integer a, Integer b, Integer c, String d) {
        StringBuilder acc = new StringBuilder();
        while (true) {
            if (a < 3 || b < 3) {
                acc.append(c.toString());
                break;
            } else if (a < 3) {
                acc.append("~");
                a = a - 1;
                b = b - 1;
                c = c + 4;
            } else if (b < 3) {
                acc.append("-").append(c).append("-");
                a = a - 2;
                b = b - 1;
                d = d + "x";
            } else {
                acc.append("/").append(d).append("/");
                a = a - 1;
                b = b - 2;
                c = c + 1;
            }
        }
        return acc.toString();
    }

    public static String fF(Integer a, Integer b, Integer c, String d) {
        StringBuilder acc = new StringBuilder();
        Stream.iterate(new Object[]{a, b, c, d}, params -> {
            Integer a1 = (Integer) params[0];
            Integer b1 = (Integer) params[1];
            Integer c1 = (Integer) params[2];
            String d1 = (String) params[3];
            if (a1 < 3 || b1 < 3) {
                return null;
            } else if (a1 < 3) {
                acc.append("~");
                return new Object[]{a1 - 1, b1 - 1, c1 + 4, d1};
            } else if (b1 < 3) {
                acc.append("-").append(c1).append("-");
                return new Object[]{a1 - 2, b1 - 1, c1, d1 + "x"};
            } else {
                acc.append("/").append(d1).append("/");
                return new Object[]{a1 - 1, b1 - 2, c1 + 1, d1};
            }
        }).takeWhile(params -> params != null)
        .forEach(params -> {});

        return acc.append(c.toString()).toString();
    }


	public static void main(String[] args) {
		Integer a = 170;
		Integer b = 20001;
		Integer c = 30000;
		String d = "aaa";
		System.out.println(f(a, b, c, d));
		System.out.println(fRF(a, b, c, d));
		System.out.println(fI(a, b, c, d));
		System.out.println(fF(a, b, c, d));
	}
}
