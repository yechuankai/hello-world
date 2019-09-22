package com.wms.web.tag;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.context.WebEngineContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import com.wms.common.utils.ParserUtils;

public class TextTagProcessor extends AbstractAttributeTagProcessor {

    private static final int PRECEDENCE = 10000;


    public TextTagProcessor(final String dialectPrefix, final String attr) {
        super(
            TemplateMode.HTML, // This processor will apply only to HTML mode
            dialectPrefix,     // Prefix to be applied to name for matching
            null,              // No tag name: match any tag name
            false,             // No prefix to be applied to tag name
            attr,         		// Name of the attribute that will be matched
            true,              // Apply dialect prefix to attribute name
            PRECEDENCE,        // Precedence (inside dialect's own precedence)
            true);             // Remove the matched attribute afterwards
    }


    @Override
    protected void doProcess(
            final ITemplateContext context, final IProcessableElementTag tag,
            final AttributeName attributeName, final String attributeValue,
            final IElementTagStructureHandler structureHandler) {
    	
    	/**
		 * 获取应用程序的上下文参数
		 */
		WebEngineContext ctx = (WebEngineContext) context;
		String text = ParserUtils.fillAttributeValue(ctx, attributeValue);
		
		structureHandler.setBody(text, false);
		
    }

}
