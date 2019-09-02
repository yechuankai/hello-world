package com.wms.web.tag;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

@Component
public class CustomTag extends AbstractProcessorDialect {
	
	private static final String PREFIX = "wms";
	private static final String TEXT = "text";
	private static final String [] ATTR = new String[] {"data-options","title", "value"};
	

    public CustomTag() {
        super("Strive Dialect", PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new LocaleElementTagProcessor(dialectPrefix));
        processors.add(new MenuElementTagProcessor(dialectPrefix));
        processors.add(new TextTagProcessor(dialectPrefix, TEXT));
        
        for (String  a : ATTR) {
        	processors.add(new AttributeTagProcessor(dialectPrefix, a));
		}
        return processors;
	}

}
