	using (var client = new HttpClient())

	{

		string base64EncodedKeyTokenPair = Base64Helpers.Base64Encode(String.Format("{0}:{1}", ApiKey, SecretKey));

		client.BaseAddress = new Uri("https://api.trustpilot.com/v1/");

		client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

		client.DefaultRequestHeaders.Authorization = AuthenticationHeaderValue.Parse(String.Format("Basic {0}", base64EncodedKeyTokenPair));

		using (FormUrlEncodedContent content = new FormUrlEncodedContent(new[]
		{
			new KeyValuePair<string, string>("grant_type", "password"),
			new KeyValuePair<string, string>("username", Username),
			new KeyValuePair<string, string>("password", Password)
		}))

		{
			HttpResponseMessage response = await client.PostAsync("oauth/oauth-business-users-for-applications/accesstoken", content);
		}

	}