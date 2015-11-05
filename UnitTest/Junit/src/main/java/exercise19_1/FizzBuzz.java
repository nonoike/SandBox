package exercise19_1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class FizzBuzz {

    public List<String> toFizzBuzzList(Long lastValue) {
        return LongStream
                .rangeClosed(1, lastValue)
                .boxed()
                .map(e -> {
                    // TODO 真偽 ? A : 真偽 ? B : C みたいな書き方できないんだっけ？
                    if (e % 15 == 0) {
                        return "FizzBuzz";
                    }
                    if (e % 5 == 0) {
                        return "Buzz";
                    }
                    if (e % 3 == 0) {
                        return "Fizz";
                    }
                    return e.toString();
                })
                .collect(Collectors.toList());
    }
}
