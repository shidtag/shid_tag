package lesson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;


public class Lesson3 {
	public static void main(String[] args) {
		//RDFモデルを作成。このmodel内にトリプルが作成されていく
		Model model = ModelFactory.createDefaultModel();
		String ex = "http://example.com/";
		String dc = "http://purl.org/dc/elements/1.1/";
		//リソースを作成。引数はURI
		Resource kokoro = model.createResource(ex + "こころ");
		Resource natsume = model.createResource(ex + "夏目漱石");
		Resource novel = model.createResource(ex + "小説");
		Resource writer = model.createResource(ex + "作家");
		Resource human = model.createResource(ex + "人間");
		//クラスとして定義
		novel.addProperty(RDF.type, RDFS.Class);
		writer.addProperty(RDF.type, RDFS.Class);
		human.addProperty(RDF.type, RDFS.Class);
		
		//クラスの階層関係を定義
		writer.addProperty(RDFS.subClassOf, human);
		//インスタンスとして定義
		kokoro.addProperty(RDF.type, novel);
		natsume.addProperty(RDF.type, writer);
		
		//プロパティを作成。引数はURI
		Property creator = model.createProperty(dc + "creator");
		
		//domain, rangeの制約を追加
		creator.addProperty(RDFS.domain, novel);
		creator.addProperty(RDFS.range, writer);
		
		//リソースにプロパティとその値を追加する（トリプルを作る）
		kokoro.addProperty(creator, natsume);
		
		//名前空間のPrefixを設定
		model.setNsPrefix("ex", ex);
		//モデルの書き出し。
		try {
			FileOutputStream fout = new FileOutputStream("test.ttl");
			model.write(fout, "TTL");
			//ファイル生成したらプロジェクトをリフレッシュしてみて。
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}