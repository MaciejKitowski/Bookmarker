package mvc.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.controller.CategoryController;
import mvc.controller.UrlController;
import mvc.view.model.CategoryView;
import mvc.view.model.UrlView;
import mvc.view.toolbar.AddNewButton;
import mvc.view.toolbar.DeleteButton;
import mvc.view.toolbar.SelectDatabaseButton;
import mvc.view.toolbar.ToolbarPanel;

public final class MainFrame extends JFrame {
	private static final long serialVersionUID = 4785143357028575468L;
	private static final Logger log = LoggerFactory.getLogger(MainFrame.class);
	
	private final String windowTitle = "Bookmarker";
	private final int defaultWidth = 800;
	private final int defaultHeight = 600;
	
	private BorderLayout layout = null;
	private final int layoutHorizontalGap = 5;
	private final int layoutVerticalGap = 5;
	
	private ToolbarPanel toolbarPanel = null;
	
	private CategoryView categoryView = null;
	private CategoryController categoryController = null;
	
	private UrlView urlView = null;
	private UrlController urlController = null;
	
	public MainFrame() {
		log.info("Initialize Main Frame with title: {} and size: {}x{}", windowTitle, defaultWidth, defaultHeight);
		
		setTitle(windowTitle);
		setSize(defaultWidth, defaultHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeLayout();
		initializeViews();
		initializeControllers();
		initializeObservers();
		
		addViewsToFrame();
	}
	
	private void initializeLayout() {
		log.debug("Initialize layout");
		
		layout = new BorderLayout();
		layout.setHgap(layoutHorizontalGap);
		layout.setVgap(layoutVerticalGap);
		
		setLayout(layout);
	}
	
	private void initializeViews() {
		log.info("Initialize views");
		
		categoryView = new CategoryView(150, defaultHeight);
		urlView = new UrlView(400,  defaultHeight);
		toolbarPanel = new ToolbarPanel(defaultWidth, 30);
	}
	
	private void initializeControllers() {
		log.info("Initialize controllers");
		
		categoryController = new CategoryController();
		urlController = new UrlController();
	}
	
	private void initializeObservers() {
		log.debug("Initalize observers");
		
		categoryController.addCategoryUpdateListener(categoryView);
		categoryView.addCategorySelectListener(urlController);
		categoryView.addCategorySelectListener(toolbarPanel.getDeleteButton());
		categoryView.addCategorySelectListener(toolbarPanel.getEditButton());
		urlView.addUrlSelectListener(toolbarPanel.getDeleteButton());
		urlController.addUrlUpdateListener(urlView);
		
		SelectDatabaseButton dbSelect = toolbarPanel.getSelectDatabaseButton();
		dbSelect.addDatabaseChangeListener(categoryController);
		dbSelect.addDatabaseChangeListener(urlController);
		
		DeleteButton del = toolbarPanel.getDeleteButton();
		del.addCategoryEditListener(categoryController);
		del.addUrlEditListener(urlController);
		
		AddNewButton add = toolbarPanel.getAddNewButton();
		add.addCategoryEditListener(categoryController);
		add.addUrlEditListener(urlController);
		
		categoryController.updateCategories();
	}
	
	private void addViewsToFrame() {
		log.info("Add views to frame");
		
		add(toolbarPanel, BorderLayout.PAGE_START);
		add(categoryView, BorderLayout.LINE_START);
		add(urlView, BorderLayout.CENTER);
	}
}
