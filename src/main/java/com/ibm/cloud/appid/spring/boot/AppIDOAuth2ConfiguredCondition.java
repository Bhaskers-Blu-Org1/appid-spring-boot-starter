package com.ibm.cloud.appid.spring.boot;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * Condition that matches if any {@code spring.security.oauth2.client.registration.appid}
 * properties are defined.
 */

public class AppIDOAuth2ConfiguredCondition extends SpringBootCondition {

	private static final String APPID = "appid";
	private static final Bindable<Map<String, AppIDOAuth2ConfigurationProperties.Registration>> STRING_REGISTRATION_MAP = Bindable
			.mapOf(String.class, AppIDOAuth2ConfigurationProperties.Registration.class);

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		ConditionMessage.Builder message = ConditionMessage.forCondition("OAuth2 Clients Configured Condition");
		Map<String, AppIDOAuth2ConfigurationProperties.Registration> registrations = getRegistrations(context.getEnvironment());
		if (!registrations.isEmpty() && registrations.containsKey(APPID)) {
			return ConditionOutcome.match(message.foundExactly("registered clients " + registrations.values().stream()
					.map(AppIDOAuth2ConfigurationProperties.Registration::getClientId).collect(Collectors.joining(", "))));
		}
		return ConditionOutcome.noMatch(message.notAvailable("registered clients"));
	}
	
	private Map<String, AppIDOAuth2ConfigurationProperties.Registration> getRegistrations(Environment environment) {
		return Binder.get(environment).bind("spring.security.oauth2.client.registration", STRING_REGISTRATION_MAP)
				.orElse(Collections.emptyMap());
	}
}
