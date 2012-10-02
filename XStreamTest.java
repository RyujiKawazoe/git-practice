package jp.co.val.sample;

import java.io.IOException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class XStreamTest {

	public static void main(String[] args) throws IOException {
		// HTTP通信してレスポンスXMLを取得
		String url = "http://192.168.30.228/webapi/v1/xml/search/range?code=22884&upperLimit=15&key=wC4SR9ETBhBcJ3Bv";
		MyConnection connection = new MyConnection();
		connection.setConnection(url, "GET");
		String xml = connection.doConnection(null);
		System.out.println(xml);
		
		// XMLからJavaオブジェクトに変換
		XStream xstream = new XStream() {
			// XMLからJavaへの変換時に不要なタグは無視する
			@Override
			protected MapperWrapper wrapMapper(MapperWrapper next) {
				return new MapperWrapper(next) {
					@Override
					public boolean shouldSerializeMember(Class definedIn, String fieldName) {
						if(definedIn == Object.class) {
							return false;
						}
						
						return super.shouldSerializeMember(definedIn, fieldName);
					}
				};
			}
		};
		xstream.processAnnotations(RangeSearch.class);
		RangeSearch result = (RangeSearch)xstream.fromXML(xml);
		
		for(Point point : result.getPointList()) {
			System.out.print(point.getStation().getCode() + ",");
			System.out.print(point.getStation().getName() + ",");
			System.out.println(point.getMinute() + "分");
		}
	}
}