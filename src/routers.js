import Home from './components/Home';
import CalculatorComponents from './components/CalculatorComponents'
import { createRouter, createWebHistory } from "vue-router";
import Contact from './components/ContactComponent';

const routes = [
    {
        name: 'Calculator',
        component: CalculatorComponents,
        path: '/Calculator',
        },
    {
        name: 'Home',
        component: Home,
        path: '/'
    },
    {
        name: 'Contact',
        component: Contact,
        path: '/Contact'
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;