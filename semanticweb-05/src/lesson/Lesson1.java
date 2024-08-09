package lesson;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;

public class Lesson1 {
	public static void main(String[] args) {
		//RDFモデルを作成。このmodel内にトリプルが作成されていく
		Model model = ModelFactory.createDefaultModel();
		String ex = "http://example.com/";
		//プロパティを作成。引数はURI
		Property age = model.createProperty(ex + "age");
		//リソースを作成。引数はURI
		Resource william = model.createResource(ex + "William");
		//リソースにプロパティとその値を追加する（トリプルを作る）
		//第１引数にプロパティ，第２引数に値
		william.addProperty(age, "22");
		//名前空間のPrefixを設定
		model.setNsPrefix("ex", ex);
		//モデルの書き出し。標準出力, RDF/XMLを指定。
		
		/* モデル書き出し方法（1） */
		//XMLで出力する場合は"TTL"を"RDF/XML"に変更
		model.write(System.out, "TTL");
		
		/* モデル書き出し方法(2) */
		//RDFWriter wr = model.getWriter();
		//wr.setProperty("showXmlDeclaration", "true");
		//wr.write(model,System.out,"RDF/XML");
	}
}
