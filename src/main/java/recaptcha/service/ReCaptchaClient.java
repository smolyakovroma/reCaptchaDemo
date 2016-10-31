package recaptcha.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import recaptcha.dto.ReCaptchaResponseDto;

@Component
public class ReCaptchaClient {

    @Value("${app.reCaptcha.apiUrl}")
    public String reCaptchaUrl;

    @Value("${app.reCaptcha.secretKey}")
    public String secretKey;

    private RestTemplate restTemplate = new RestTemplate();

    public ReCaptchaResponseDto verify(@NonNull String recaptchaResponse) {

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secretKey);
        form.add("response", recaptchaResponse+"qwewqe");
        return restTemplate.postForObject(reCaptchaUrl, form, ReCaptchaResponseDto.class);
    }
}
