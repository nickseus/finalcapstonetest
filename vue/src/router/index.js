import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Books from '../views/Books.vue'
import NewBook from '../views/NewBook.vue'
import FamilyAccount from '../views/FamilyAccount.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import BookDetails from '../views/BookDetails.vue'
import Prizes from '../views/Prizes.vue'
import BookReadingActivity from '../views/BookReadingActivity.vue'
import SearchBook from '../views/SearchBook.vue'
import FamilyMemberReadingList from '../components/FamilyMemberReadingList.vue'
//import ReadingList from '../components/ReadingList.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/books',
      name: 'books',
      component: Books,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/searchBook',
      name: 'searchbook',
      component: SearchBook,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/book/:book_id/book-reading-activity',
      name: 'bookReadingActivity',
      component: BookReadingActivity,
      meta: {
        requiresAuth: true
      }

    },
    
    {
      path: '/prizes',
      name: 'prizes',
      component: Prizes,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/book/:book_id',
      name: 'book-details',
      component: BookDetails,
      meta: {
        requiresAuth: true
      }
    },

{
      path: '/addBook',
      name: 'add-book',
      component: NewBook,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/familyAccount',
      name: 'family-account',
      component: FamilyAccount,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: `/books/listbooks/:id`,
      name: 'familymember-books',
      component: FamilyMemberReadingList,
      meta: {
        requiresAuth: true
      }
    },

    
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },

   
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
