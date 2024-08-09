package lesson;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Lesson2 {
	public static void main(String[] args) {
		//RDFモデルを作成。このmodel内にトリプルが作成されていく
		Model model = ModelFactory.createDefaultModel();
		String ex = "http://example.com/";
		//プロパティを作成。引数はURI
		Property hasFather = model.createProperty(ex + "hasFather");
		//リソースを作成。引数はURI
		Resource william = model.createResource(ex + "William");
		Resource charles = model.createResource(ex + "Charles");
		//リソースにプロパティとその値を追加する（トリプルを作る）
		william.addProperty(hasFather, charles);
		//名前空間のPrefixを設定
		model.setNsPrefix("ex", ex);
		//モデルの書き出し。標準出力を指定。
		model.write(System.out, "TTL");
	}
}
