package us.lsi.clase.mezclaordenada;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MezclaOrdenadaTest {

    @Test
    public void shouldMergeTwoSortedListsIntoOne() {
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(expected, MezclaOrdenada.mezclaOrdenada(list1, list2, Comparator.naturalOrder()));
    }

    @Test
    public void shouldHandleEmptyLists() {
        List<Integer> list1 = Arrays.asList();
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8);
        List<Integer> expected = Arrays.asList(2, 4, 6, 8);
        assertEquals(expected, MezclaOrdenada.mezclaOrdenada(list1, list2, Comparator.naturalOrder()));
    }

    @Test
    public void shouldHandleListsOfDifferentSizes() {
        List<Integer> list1 = Arrays.asList(1, 3);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 6, 8);
        assertEquals(expected, MezclaOrdenada.mezclaOrdenada(list1, list2, Comparator.naturalOrder()));
    }

    @Test
    public void shouldHandleListsWithDuplicateValues() {
        List<Integer> list1 = Arrays.asList(1, 3, 3, 7);
        List<Integer> list2 = Arrays.asList(2, 3, 6, 8);
        List<Integer> expected = Arrays.asList(1, 2, 3, 3, 3, 6, 7, 8);
        assertEquals(expected, MezclaOrdenada.mezclaOrdenada(list1, list2, Comparator.naturalOrder()));
    }
}
