package fr.xebia.xke.java8.step4;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class NumberUtilsTest {
    @Test
    public void should_return_100_random_number_when_seed_is_three() {

        int[] randomValues = NumberUtils.generateRandom(100, 3L);

        assertThat(randomValues).hasSize(100);
        assertThat(randomValues).isEqualTo(new int[]{734, 660, 210, 581, 128, 202, 549, 564, 459, 961, 585, 882, 277, 614, 981, 806, 576, 137, 886, 99, 911, 92, 385, 795, 278, 913, 357, 259, 946, 793, 638, 635, 922, 768, 192, 417, 561, 134, 153, 787, 857, 89, 632, 74, 270, 142, 928, 135, 724, 414, 539, 421, 157, 589, 229, 238, 507, 246, 430, 610, 526, 425, 75, 186, 571, 687, 995, 922, 850, 709, 265, 780, 946, 395, 930, 426, 403, 593, 620, 995, 199, 983, 344, 631, 326, 882, 198, 790, 692, 671, 180, 882, 609, 56, 779, 269, 814, 843, 127, 444});

    }
}
