package ru.practicum.dinner;

public class ComboGenerator {
    /**
     * Для набора чисел {@code variations}, задающего количество возможных выборов на каждой из позиций в
     * комбинации,
     * устанавливает соответствие (биективное отображение) между числами от 0 до [количество возможных комбинаций -
     * 1] и всеми возможными комбинациями.
     *
     * @param combo      число (номер комбинации);
     * @param variations набор чисел, определяющий возможные комбинации;
     * @return комбинация, соответствующая числу {@code combo}.
     */
    public static int[] readCombo(int combo, int[] variations) {
        int[] result = new int[variations.length];
        for (int i = variations.length - 1; i >= 0; i--) {
            result[i] = combo % variations[i];
            combo /= variations[i];
        }
        return result;
    }
}
