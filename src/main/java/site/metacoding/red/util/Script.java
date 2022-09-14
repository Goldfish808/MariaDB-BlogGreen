package site.metacoding.red.util;

public class Script {
	public static String back(String msg) { // 여러 스레드가 몰려도 back 이라는 이름이 Static에 의해 메모리에 뜨지, 안의 sb 는 각자 뜸
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String href(String url) { //
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String href(String url, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
	}

}
