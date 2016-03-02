package com.trustpilot.support.restclient.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrustPilotAPIDefs {

	public static List<TrustPilotAPI> customer_apis  = new ArrayList<TrustPilotAPI>() {
		{

			add(new TrustPilotAPI(
					"Get Product Reviews Conversations API",
					"https://api.trustpilot.com/v1/conversations/{conversationId}",
					"Ex: Create product review invitation link",
					"http://apps.trustpilot.com/client/apis/get/product-reviews/business-units/%7BbusinessUnitId%7D/reviews",
					"GET"
					));


			add(new TrustPilotAPI(
					"Get Business Unit Product Reviews Summaries List",
					"https://api.trustpilot.com/v1/private/product-reviews/business-units/{businessUnitId}/summaries",
					"Ex: Get Business Unit Product Reviews Summaries List",
					"http://apps.trustpilot.com/client/apis/get/product-reviews/business-units/%7BbusinessUnitId%7D/summaries",
					"GET"
					));

			add(new TrustPilotAPI(
					"Get business unit product private reviews",
					"https://api.trustpilot.com/v1/private/product-reviews/business-units/{businessUnitId}/reviews",
					"Ex: Get business unit product private reviews",
					"http://apps.trustpilot.com/client/apis/get/private/product-reviews/business-units/%7BbusinessUnitId%7D/reviews",
					"GET"
					));

			add(new TrustPilotAPI(
					"Create product review invitation link",
					"https://api.trustpilot.com/v1/private/product-reviews/business-units/{businessUnitId}/invitation-links?email={email}&name={name}&referenceId={referenceId}&locale={locale}&productUrl={productUrl}",
					"Ex: Create product review invitation link",
					"http://apps.trustpilot.com/client/apis/post/product-reviews/business-units/%7BbusinessUnitId%7D/invitation-links",
					"POST"
					));

			
			
			add(new TrustPilotAPI(
					"Delete a public tag from a review",
					"https://api.trustpilot.com/v1/private/reviews/{reviewId}/tags",
					"Ex: Removes a single tag from a particular review",
					"http://apps.trustpilot.com/client/apis/post/product-reviews/business-units/%7BbusinessUnitId%7D/invitation-links",
					"DELETE"
					));
			
			add(new TrustPilotAPI(
					"Create tag for a review",
					"https://api.trustpilot.com/v1/private/reviews/{reviewId}/tags",
					"Ex: Create tag for a review",
					"http://apps.trustpilot.com/client/apis/post/reviews/%7BreviewId%7D/tags",
					"POST"
					));
			
			add(new TrustPilotAPI(
					"Get all tags for a particular review",
					"https://api.trustpilot.com/v1/private/reviews/{reviewId}/tags",
					"Ex: Get all tags for a particular review",
					"http://apps.trustpilot.com/client/apis/get/reviews/%7BreviewId%7D/tags",
					"GET"
					));
			
			add(new TrustPilotAPI(
					"Get review including private data",
					"https://api.trustpilot.com/v1/private/reviews/{reviewId}",
					"Ex: Get review including private data",
					"http://apps.trustpilot.com/client/apis/get/reviews/%7BreviewId%7D",
					"GET"
					));
			
			add(new TrustPilotAPI(
					"Reply to a review",
					"https://api.trustpilot.com/v1/private/reviews/{reviewId}/reply",
					"Ex: Reply to a review",
					"http://apps.trustpilot.com/client/apis/post/reviews/%7BreviewId%7D/reply",
					"POST"
					));
			
			add(new TrustPilotAPI(
					"Get the business unit reviews",
					"https://api.trustpilot.com/v1/private/business-units/{business-unit-id}/reviews",
					"Ex: Get the business unit reviews",
					"http://apps.trustpilot.com/client/apis/get/business-units/%7Bbusiness-unit-id%7D/reviews",
					"POST"
					));
			
			add(new TrustPilotAPI(
					"Generate service review invitation link",
					"https://invitations-api.trustpilot.com/v1/private/business-units/{business-unit-id}/invitation-links?email={email}&name={name}&referenceId={referenceId}&locale={locale}",
					"Ex: Generate service review invitation link",
					"http://apps.trustpilot.com/client/apis/get/business-units/%7Bbusiness-unit-id%7D/reviews",
					"POST"
					));
			
			add(new TrustPilotAPI(
					"Create new invitation",
					"https://invitations-api.trustpilot.com/v1/private/business-units/{business-unit-id}/invitations",
					"Ex: Create new invitation",
					"http://apps.trustpilot.com/invitations/apis/post/business-units/%7Bbusiness-unit-id%7D/invitations?recipeintEmail={recipeintEmail}&recipeintName={recipeintName}&templateId={templateId}&locale={locale}&senderEmail={senderEmail}&senderName={senderName}&replyTo={replyTo}&preferredSendTime={preferredSendTime}",
					"POST"
					));			

			add(new TrustPilotAPI(
					"Get list of invitation templates",
					"https://invitations-api.trustpilot.com/v1/private/business-units/{business-unit-id}/templates",
					"Ex: Get list of invitation templates",
					"http://apps.trustpilot.com/invitations/apis/get/business-units/%7Bbusiness-unit-id%7D/templates",
					"GET"
					));	


		}


	};

	public static TrustPilotAPI getCustomerAPI(String name) {
		Iterator<TrustPilotAPI> itr = customer_apis.iterator();
		while(itr.hasNext()){
			TrustPilotAPI api = itr.next();
			if(name.equals(api.getName()))
				return api;
		}
		return null;
	}

}
