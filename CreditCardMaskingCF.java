import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreditCardMaskingCF {

    public static void main(String[] args) {

        String creditCardNumber = "1234567812345678";

        CompletableFuture<String> maskedFuture =
                CompletableFuture.supplyAsync(() ->
                        IntStream.range(0, creditCardNumber.length())
                                .mapToObj(i -> i >= creditCardNumber.length() - 5
                                        ? '9'
                                        : creditCardNumber.charAt(i))
                                .map(String::valueOf)
                                .collect(Collectors.joining())
                );

        // Main thread waits for child thread result
        String maskedCard = maskedFuture.join();

        System.out.println("Original Card : " + creditCardNumber);
        System.out.println("Masked Card   : " + maskedCard);
    }
}
