package shoppingApp.model;
import java.util.Vector;

/** A catalog listing the items available in inventory. */
public class ProductList extends Vector {
	
	
	// This would come from a database in real life
	private static Product[] products = {
			new Product("hall001", "<I>Core Servlets and JavaServer Pages</I> " + " by Marty Hall",40,39.95,
					"The definitive reference on servlets " + "and JSP from Prentice Hall and \n"
							+ "Sun Microsystems Press. Nominated for " + "the Nobel Prize in Literature."),
			new Product("hall002",
					"<I>Core Web Programming, Java2 Edition</I> " + "by Marty Hall, Larry Brown, and " + "Paul McNamee",40,39.95,
					"One stop shopping for the Web programmer. " + "Topics include \n"
							+ "<UL><LI>Thorough coverage of Java 2; " + "including Threads, Networking, Swing, \n"
							+ "Java2D, and Collections\n" + "<LI>A fast introduction to HTML 4.01, "
							+ "including frames, style sheets, layers,\n" + "and Netscape and Internet Explorer "
							+ "extensions.\n" + "<LI>A fast introduction to HTTP 1.1, "
							+ "servlets, and JavaServer Pages.\n" + "<LI>A quick overview of JavaScript 1.2\n"
							+ "</UL>"),
			new Product("lewis001", "<I>The Chronicles of Narnia</I> by C.S. Lewis", 40, 39.95,
					"The classic children's adventure pitting " + "Aslan the Great Lion and his followers\n"
							+ "against the White Witch and the forces " + "of evil. Dragons, magicians, quests, \n"
							+ "and talking animals wound around a deep " + "spiritual allegory. Series includes\n"
							+ "<I>The Magician's Nephew</I>,\n" + "<I>The Lion, the Witch and the " + "Wardrobe</I>,\n"
							+ "<I>The Horse and His Boy</I>,\n" + "<I>Prince Caspian</I>,\n"
							+ "<I>The Voyage of the Dawn " + "Treader</I>,\n" + "<I>The Silver Chair</I>, and \n"
							+ "<I>The Last Battle</I>."),
			new Product("alexander001", "<I>The Prydain Series</I> by Lloyd Alexander", 40, 39.95,
					"Humble pig-keeper Taran joins mighty " + "Lord Gwydion in his battle against\n"
							+ "Arawn the Lord of Annuvin. Joined by " + "his loyal friends the beautiful princess\n"
							+ "Eilonwy, wannabe bard Fflewddur Fflam," + "and furry half-man Gurgi, Taran discovers "
							+ "courage, nobility, and other values along\n" + "the way. Series includes\n"
							+ "<I>The Book of Three</I>,\n" + "<I>The Black Cauldron</I>,\n"
							+ "<I>The Castle of Llyr</I>,\n" + "<I>Taran Wanderer</I>, and\n" + "<I>The High King</I>."),
			new Product("rowling001", "<I>The Harry Potter Trilogy</I> by " + "J.K. Rowling", 40, 39.95,
					"The first three of the popular stories " + "about wizard-in-training Harry Potter\n"
							+ "topped both the adult and children's " + "best-seller lists. Series includes\n"
							+ "<I>Harry Potter and the " + "Sorcerer's Stone</I>,\n" + "<I>Harry Potter and the "
							+ "Chamber of Secrets</I>, and\n" + "<I>Harry Potter and the " + "Prisoner of Azkaban</I>.") };

	public ProductList() {
		
		for (int i = 0; i < products.length; i++)
		{
			this.add(products[i]);
		}
	}
	
	public static Vector getAllProducts() {
		Vector productList=new Vector<>();
		
		for (int i = 0; i < products.length; i++)
		{
			productList.add(i,products[i]);			
		}
		return (productList);
	}

	public static Product getProduct(String sku) {
		Product product;
		if (sku == null) {
			return (null);
		}
		for (int i = 0; i < products.length; i++) {
			product = products[i];
			if (sku.equals(product.getSKU())) {
				return (product);
			}
		}
		return (null);
	}
	public static void setProduct(Product updatedProduct) {
		Product product;
		if (updatedProduct == null) {
			return;
		}
		for (int i = 0; i < products.length; i++) {
			product = products[i];
			if (updatedProduct.getSKU().equals(product.getSKU())) {
				products[i]=updatedProduct;
			}
		}
		//return (null);
	}
}
