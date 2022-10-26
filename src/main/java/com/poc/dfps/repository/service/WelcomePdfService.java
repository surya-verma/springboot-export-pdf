package com.poc.dfps.repository.service;

import com.poc.dfps.model.WelcomeData;
import com.poc.dfps.util.HTMLToPDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayInputStream;

@Service
public class WelcomePdfService {
	@Autowired
	private FreeMarkerConfigurer freemarkerConfig;

	public ByteArrayInputStream sendWelcomeEmail(WelcomeData welcomeData) {
		try {
			String templateContent = FreeMarkerTemplateUtils
					.processTemplateIntoString(freemarkerConfig.getConfiguration()
							.getTemplate("welcome.ftlh"),
							welcomeData.getDataMap());
			ByteArrayInputStream bis = HTMLToPDFGenerator.generatePDFFromHTML(templateContent);
			return bis;
		} catch (Exception e) {
			System.out.println("Sending welcome pdf failed, check log...");
			e.printStackTrace();
		}
		return null;
	}
}
