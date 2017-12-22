package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Scrollbar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CategoryView extends JPanel {
	private static final long serialVersionUID = 8970054597563459574L;
	private static final Logger log = LoggerFactory.getLogger(CategoryView.class);
	
	public CategoryView(int width, int height) {
		log.info("Initialize Category view");
		
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(width, height));
		setBorder(new TitledBorder("Categories"));
		
		setLayout(new BorderLayout(5,5));
		
		//generateTestList();
		generateList();
	}
	
	private void generateList() {
		log.debug("Generate list");
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		
		DefaultMutableTreeNode mainA = new DefaultMutableTreeNode("Parent 1");
		for(int i = 0; i < 50; ++i) {
			DefaultMutableTreeNode childA = new DefaultMutableTreeNode("Child " + i);
			mainA.add(childA);
		}
		
		DefaultMutableTreeNode mainB = new DefaultMutableTreeNode("Parent 2");
		for(int i = 0; i < 50; ++i) {
			DefaultMutableTreeNode childA = new DefaultMutableTreeNode("Child " + i);
			mainB.add(childA);
		}
		
		root.add(mainA);	
		root.add(mainB);
		
		JTree tree = new JTree(root);
		tree.setRootVisible(false);
		
		JScrollPane scrollPane = new JScrollPane(tree);
		
		add(scrollPane);
	}
	
	private void generateTestList() {
		log.debug("Generate list");
		
		//DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		
		DefaultMutableTreeNode mainA = new DefaultMutableTreeNode("Parent 1");
		
		for(int i = 0; i < 50; ++i) {
			DefaultMutableTreeNode childA = new DefaultMutableTreeNode("Child " + i);
			mainA.add(childA);
		}
		
		
		/*DefaultMutableTreeNode mainB = new DefaultMutableTreeNode("Parent 2");
		
		DefaultMutableTreeNode childA = new DefaultMutableTreeNode("Child 1");
		DefaultMutableTreeNode childB = new DefaultMutableTreeNode("Child 2");
		DefaultMutableTreeNode childC = new DefaultMutableTreeNode("Child 3");
		DefaultMutableTreeNode childD = new DefaultMutableTreeNode("Child 4");
		
		mainA.add(childA);
		mainA.add(childB);
		mainA.add(childC);*/
		
		JTree tree = new JTree(mainA);
		
		JScrollPane scrollPane = new JScrollPane(tree);

	
		add(scrollPane);
	}
}
