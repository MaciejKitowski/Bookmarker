package mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.List;
import java.util.Map;

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

import mvc.controller.observer.category.CategoryChangedListener;
import mvc.model.Category;
import mvc.model.MainCategory;

public final class CategoryView extends JPanel implements CategoryChangedListener {
	private static final long serialVersionUID = 8970054597563459574L;
	private static final Logger log = LoggerFactory.getLogger(CategoryView.class);
	private static final boolean rootVisible = false;
	private static final int toggleClickCount = 1;
	
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
	}
	
	@Override
	public void onCategoryChanged(Map<MainCategory, List<Category>> categories) {
		log.debug("Categories changed");
		setTreeList(categories);
	}
	
	private void initializeListTree() {
		log.debug("Initialize category list tree");
		
		treeRoot = new DefaultMutableTreeNode("Categories");
		treeList = new JTree(treeRoot);
		treeScrollbar = new JScrollPane(treeList);
		
		treeList.setRootVisible(rootVisible);
		treeList.setToggleClickCount(toggleClickCount);
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
	
	private void setTreeList(Map<MainCategory, List<Category>> categories) {
		log.debug("Add {} nodes to tree list", categories.size());
		
		for(Map.Entry<MainCategory, List<Category>> entry : categories.entrySet()) {
			log.debug("Add main category (ID={} name={}) as node", entry.getKey().getID(), entry.getKey().getName());
			
			DefaultMutableTreeNode main = new DefaultMutableTreeNode(entry.getKey().getName());
			
			for(Category cat : entry.getValue()) {
				log.debug("Add subcategory (ID={} name={}) to category (ID={} name={})", cat.getID(), cat.getName(), entry.getKey().getID(), entry.getKey().getName());
				
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(cat.getName());
				main.add(child);
			}
			
			treeRoot.add(main);
		}
		
		refreshTreeList();
	}
		
	private void refreshTreeList() { //Tree have to be refreshed after add new node
		DefaultTreeModel model = (DefaultTreeModel)treeList.getModel();
		model.reload();
	}
}
