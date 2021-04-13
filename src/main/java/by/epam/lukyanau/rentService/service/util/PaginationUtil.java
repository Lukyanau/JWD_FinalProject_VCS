package by.epam.lukyanau.rentService.service.util;

import java.util.List;

public class PaginationUtil {
    private static final int DEFAULT_START_INDEX_SPLIT = 0;

    public static <T> List<T> takeSplitListByPage(List<T> currentList, int splitNumber, int pageNumber) {
        List<T> subList;
        int totalPages = calculateTotalPages(currentList, splitNumber);
        if (pageNumber > totalPages) {
            subList = currentList.subList(DEFAULT_START_INDEX_SPLIT, splitNumber);
        } else {
            int endSub;
            int startSub = splitNumber * (pageNumber - 1);
            if (totalPages == pageNumber) {
                endSub = currentList.size();
            } else {
                endSub = splitNumber * pageNumber;
            }
            subList = currentList.subList(startSub, endSub);
        }
        return subList;
    }

    public static <T> int calculateTotalPages(List<T> currentList, int splitNumber) {
        int currentLengthList = currentList.size();
        int maxPages = currentLengthList / splitNumber;
        if (currentLengthList % splitNumber > 0) {
            maxPages++;
        }
        return maxPages;
    }
}
