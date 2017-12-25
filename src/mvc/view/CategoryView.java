package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URI;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CategoryView extends JPanel {
	private static final long serialVersionUID = 8970054597563459574L;
	private static final Logger log = LoggerFactory.getLogger(CategoryView.class);
	
	
	private DefaultMutableTreeNode treeRoot = null;
	private JTree treeList = null;
	private JScrollPane treeScrollbar = null;
	
	public CategoryView(int width, int height) {
		log.info("Initialize Category view");
		
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(width, height));
		setBorder(new TitledBorder("Categories"));
		
		setLayout(new BorderLayout(5,5));
		
		initializeListTree();
		setTreeListStyle();
		add(treeScrollbar);
		
		testInsert();
	}
	
	private void initializeListTree() {
		log.debug("Initialize category list tree");
		
		treeRoot = new DefaultMutableTreeNode("Categories");
		treeList = new JTree(treeRoot);
		treeScrollbar = new JScrollPane(treeList);
		
		treeList.setRootVisible(false);
	}
	
	private void setTreeListStyle() {
		log.debug("Set tree list style");
		
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)treeList.getCellRenderer();
		
		renderer.setBackgroundNonSelectionColor(Color.LIGHT_GRAY);
		renderer.setBackgroundSelectionColor(Color.WHITE);
		
		try {
			URL nodeClosedIcon = getClass().getResource("/icons/treelist_node_closed.png");
			URL nodeOpenedIcon = getClass().getResource("/icons/treelist_node_opened.png");
			URL nodeLeafIcon = getClass().getResource("/icons/treelist_leaf.png");
			
			renderer.setClosedIcon(new ImageIcon(nodeClosedIcon));
			renderer.setOpenIcon(new ImageIcon(nodeOpenedIcon));
			renderer.setLeafIcon(new ImageIcon(nodeLeafIcon));
		}
		catch(Exception ex) {
			log.error("Failed to load icons", ex);
		}
						
		treeList.setBackground(Color.LIGHT_GRAY);
	}
	
	private void testInsert() {
		log.warn("Test insert");
		
		DefaultMutableTreeNode mainA = new DefaultMutableTreeNode("Parent 1");
		for(int i = 0; i < 50; ++i) {
			DefaultMutableTreeNode childA = new DefaultMutableTreeNode("Child " + i);
			mainA.add(childA);
		}
		
		treeRoot.add(mainA);

		DefaultMutableTreeNode mainB = new DefaultMutableTreeNode("Parent 2");
		for(int i = 0; i < 50; ++i) {
			DefaultMutableTreeNode childA = new DefaultMutableTreeNode("Child " + i);
			mainB.add(childA);
		}
		
		treeRoot.add(mainB);
		
		refreshTreeList();
	}
	
	private void refreshTreeList() { //Tree have to be refreshed after add new node
		DefaultTreeModel model = (DefaultTreeModel)treeList.getModel();
		model.reload();
	}
}
