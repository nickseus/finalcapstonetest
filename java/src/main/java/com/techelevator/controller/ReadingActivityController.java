package com.techelevator.controller;

import com.techelevator.dao.PrizeDao;
import com.techelevator.dao.PrizeWinnerDao;
import com.techelevator.dao.ReadingActivityDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Prize;
import com.techelevator.model.PrizeWinner;
import com.techelevator.model.ReadingActivity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ReadingActivityController {

    private ReadingActivityDao readingActivityDao;
    private PrizeDao prizeDao;
    private PrizeWinnerDao prizeWinnerDao;
    private UserDao userDao;

    public ReadingActivityController(ReadingActivityDao readingActivityDao, PrizeDao prizeDao, PrizeWinnerDao prizeWinnerDao, UserDao userDao) {
        this.readingActivityDao = readingActivityDao;
        this.prizeDao = prizeDao;
        this.prizeWinnerDao = prizeWinnerDao;
        this.userDao = userDao;
    }

    /**
     *
     *add reading activity
     *
     * @param readingActivity
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/addReadingActivity", method = RequestMethod.POST)
    public ReadingActivity addReadingActivity(@Valid @RequestBody ReadingActivity readingActivity) {
        return readingActivityDao.createReadingActivity(readingActivity);
    }

    /**
     *
     *
     * Return Reading Activity
     * @param id reading activity id
     * @return reading activity information
     */
//    `/books/reading_activity/${book_id}
    @RequestMapping(path = "/books/reading_activity/{id}", method = RequestMethod.GET)
    public ReadingActivity getActivity(@PathVariable int id, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        ReadingActivity readingActivity = readingActivityDao.getReadingActivity(id, loggedInUserId);
            if (readingActivity == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to locate Reading Activity.");
            } else {
                return readingActivity;
            }
        }

    /**
     *
     *
     * Return Reading Activity By Book
      * @param bookId book id
     * @return reading information
     */
//    `/books/reading_activity/${book_id}
     @RequestMapping(path = "/reading_activity/books/{book_id}", method = RequestMethod.GET)
     public ReadingActivity getActivityByBook(@PathVariable int bookId) {
        ReadingActivity readingActivity = readingActivityDao.getReadingActivityByBookId(bookId);
        if (readingActivity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to locate Reading Activity for this Book.");
        } else {
            return readingActivity;
        }
     }

    /**
     * Updates logged in users reading activity by book id
     *
     * @param readingActivity
     * @return updated reading activity for book by user
     */
     @RequestMapping(path = "update/reading_activity/book/{id}", method = RequestMethod.PUT)
     public ReadingActivity updateLoggedInUserReadingActivityByBookId(@Valid @RequestBody ReadingActivity readingActivity, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
         ReadingActivity updateBookActivity = readingActivityDao.updateReadingActivity(readingActivity, loggedInUserId);
         if (updateBookActivity == null) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update Reading Activity.");
         } else {
             return updateBookActivity;
         }
     }

    /**
     *
     * return list of logged users reading activity
     *
     * @param principal
     * @return
     */
    @RequestMapping(path = "reading_activity/list", method = RequestMethod.GET)
     public List<ReadingActivity> listReadingActivityByLoggedInUser(Principal principal) {
         int loggedInUserId = userDao.findIdByUsername(principal.getName());
         List<ReadingActivity> readingList = readingActivityDao.getReadingActivitiesByUserId(loggedInUserId);
         if (readingList == null) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reading Activity List not found.");
         } else {
             return readingList;
         }
     }


}

