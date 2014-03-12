package fr.xebia.xke.java8.step4;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsTest {

    @Test
    public void should_return_100_random_number_when_seed_is_three() {

        int[] randomValues = NumberUtils.generateRandom(100, 3L);

        assertThat(randomValues).hasSize(100);
        assertThat(randomValues).isEqualTo(new int[]{734, 660, 210, 581, 128, 202, 549, 564, 459, 961, 585, 882, 277, 614, 981, 806, 576, 137, 886, 99, 911, 92, 385, 795, 278, 913, 357, 259, 946, 793, 638, 635, 922, 768, 192, 417, 561, 134, 153, 787, 857, 89, 632, 74, 270, 142, 928, 135, 724, 414, 539, 421, 157, 589, 229, 238, 507, 246, 430, 610, 526, 425, 75, 186, 571, 687, 995, 922, 850, 709, 265, 780, 946, 395, 930, 426, 403, 593, 620, 995, 199, 983, 344, 631, 326, 882, 198, 790, 692, 671, 180, 882, 609, 56, 779, 269, 814, 843, 127, 444});

    }

    @Test
    public void should_split_even_and_odd_number() {

        int[] randomValues = NumberUtils.generateRandom(100, 3L);

        Map<Boolean, List<Integer>> evenAndOddNumbers = NumberUtils.splitEvenAndOddNumber(randomValues);

        List<Integer> evenNumber = evenAndOddNumbers.get(Boolean.TRUE);
        List<Integer> oddNumber = evenAndOddNumbers.get(Boolean.FALSE);

        assertThat(evenNumber).isEqualTo(Arrays.asList(734, 660, 210, 128, 202, 564, 882, 614, 806, 576, 886, 92, 278, 946, 638, 922, 768, 192, 134, 632, 74, 270, 142, 928, 724, 414, 238, 246, 430, 610, 526, 186, 922, 850, 780, 946, 930, 426, 620, 344, 326, 882, 198, 790, 692, 180, 882, 56, 814, 444));
        assertThat(oddNumber).isEqualTo(Arrays.asList(581, 549, 459, 961, 585, 277, 981, 137, 99, 911, 385, 795, 913, 357, 259, 793, 635, 417, 561, 153, 787, 857, 89, 135, 539, 421, 157, 589, 229, 507, 425, 75, 571, 687, 995, 709, 265, 395, 403, 593, 995, 199, 983, 631, 671, 609, 779, 269, 843, 127));

    }
}
