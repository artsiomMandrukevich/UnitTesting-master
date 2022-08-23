package base;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import static base.BaseTest.screenshot;
import static base.BaseTest.browserInfo;

public class AllureTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        if (extensionContext.getExecutionException().isPresent()) {
            Allure.addAttachment("Browser info ", browserInfo);
            Allure.addByteAttachmentAsync(
                    "Page screenshot",
                    "image/png",
                    () -> screenshot
            );
        }
    }
}
