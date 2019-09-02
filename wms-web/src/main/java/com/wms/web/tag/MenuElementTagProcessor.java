package com.wms.web.tag;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.wms.web.utils.MenuUtils;

public class MenuElementTagProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "menu";
	
	private static final int PRECEDENCE = 1000;

	public MenuElementTagProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {

		String menu = MenuUtils.loadUserMenu();

		structureHandler.replaceWith(menu, false);

	}

}
