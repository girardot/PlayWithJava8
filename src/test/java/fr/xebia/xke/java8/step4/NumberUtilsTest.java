package fr.xebia.xke.java8.step4;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fest.assertions.api.Assertions.assertThat;

public class NumberUtilsTest {
    @Test
    public void should_return_100_random_number_when_seed_is_three() {

        Integer[] randomValues = NumberUtils.generateUniqueRandom(100, 3L);

        Set<Integer> randomUniqueValues = Arrays.stream(randomValues).collect(Collectors.toSet());
        assertThat(randomUniqueValues).hasSize(100);
        assertThat(randomValues).isEqualTo(new Integer[]{768, 259, 265, 779, 780, 269, 270, 526, 787, 277, 278, 790, 793, 795, 539, 546, 549, 806, 814, 561, 564, 56, 571, 576, 581, 326, 585, 74, 75, 843, 589, 593, 850, 344, 857, 89, 92, 609, 610, 99, 357, 614, 620, 882, 886, 631, 632, 635, 379, 638, 127, 128, 896, 385, 134, 135, 137, 395, 142, 911, 399, 913, 403, 660, 153, 922, 157, 414, 671, 928, 417, 930, 421, 425, 426, 430, 687, 946, 692, 180, 186, 444, 192, 961, 193, 709, 198, 199, 202, 459, 210, 724, 981, 983, 734, 995, 229, 238, 246, 507});

    }
}
