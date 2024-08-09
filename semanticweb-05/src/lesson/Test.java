package lesson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

public class Test {
	public static void main(String[] args) {
		//RDFモデルを作成。このmodel内にトリプルが作成されていく
		Model model = ModelFactory.createDefaultModel();
		String ex = "http://example.com/";
		String wd = "http://www.wikidata.org/entity/";
		String wdt = "http://www.wikidata.org/prop/direct/";
		//プロパティを作成。引数はURI
		Property birth_of_place = model.createProperty(wdt + "P19");
		Property country = model.createProperty(wdt + "P17");
		Property dantai_code = model.createProperty(wdt + "P429");
		//リソースを作成。引数はURI
		Resource a_san = model.createResource(ex + "Aさん");
		Resource tokyo = model.createResource(wd + "Q1490");
		Resource person = model.createResource(wd + "Q215627");
		Resource japan = model.createResource(wd + "Q17");
		//リソースにプロパティとその値を追加する（トリプルを作る）
		//第１引数にプロパティ，第２引数に値
		a_san.addProperty(RDF.type, person);
		a_san.addProperty(birth_of_place, tokyo);
		tokyo.addProperty(country, japan);
		tokyo.addProperty(dantai_code, "130001");
		//名前空間のPrefixを設定
		model.setNsPrefix("ex", ex);
		model.setNsPrefix("wd", wd);
		model.setNsPrefix("wdt", wdt);
		//モデルの書き出し。Turtleを指定
		//モデルの書き出し。
		try {
			FileOutputStream fout = new FileOutputStream("test.ttl");
			model.write(fout, "TTL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
