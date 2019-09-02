package com.wms.web.tag;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

public class LocaleElementTagProcessor extends AbstractElementTagProcessor {

	private static final String TAG_NAME = "locale";
	private static final String PRO_NAME = "name";
	private static final int PRECEDENCE = 1000;

	public LocaleElementTagProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, TAG_NAME, true, null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {

		/**
		 * 获取应用程序的上下文参数
		 */
		WebEngineContext ctx = (WebEngineContext) context;

		final String name = tag.getAttributeValue(PRO_NAME);
		
		Object label = ctx.getVariable(name);
		if (label == null) {
			label = name;
		}

		final IModelFactory modelFactory = context.getModelFactory();
		final IModel model = modelFactory.createModel();

		model.add(modelFactory.createText(label.toString()));

		// 将元素替换为指定的模型
		structureHandler.replaceWith(model, false);

	}

}
