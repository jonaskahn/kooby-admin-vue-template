import BlockViewer from '@/components/BlockViewer.vue';
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import AutoComplete from 'primevue/autocomplete';
import Avatar from 'primevue/avatar';
import AvatarGroup from 'primevue/avatargroup';
import Badge from 'primevue/badge';
import BlockUI from 'primevue/blockui';
import Breadcrumb from 'primevue/breadcrumb';
import Button from 'primevue/button';
import ButtonGroup from 'primevue/buttongroup';
import Calendar from 'primevue/calendar';
import Card from 'primevue/card';
import Chart from 'primevue/chart';
import Carousel from 'primevue/carousel';
import CascadeSelect from 'primevue/cascadeselect';
import Checkbox from 'primevue/checkbox';
import Chip from 'primevue/chip';
import Chips from 'primevue/chips';
import ColorPicker from 'primevue/colorpicker';
import Column from 'primevue/column';
import ColumnGroup from 'primevue/columngroup';
import ConfirmDialog from 'primevue/confirmdialog';
import ConfirmPopup from 'primevue/confirmpopup';
import ContextMenu from 'primevue/contextmenu';
import DataTable from 'primevue/datatable';
import DataView from 'primevue/dataview';
import DataViewLayoutOptions from 'primevue/dataviewlayoutoptions';
import DeferredContent from 'primevue/deferredcontent';
import Dialog from 'primevue/dialog';
import Divider from 'primevue/divider';
import Dock from 'primevue/dock';
import Dropdown from 'primevue/dropdown';
import DynamicDialog from 'primevue/dynamicdialog';
import Fieldset from 'primevue/fieldset';
import FileUpload from 'primevue/fileupload';
import FloatLabel from 'primevue/floatlabel';
import Galleria from 'primevue/galleria';
import IconField from 'primevue/iconfield';
import Image from 'primevue/image';
import InlineMessage from 'primevue/inlinemessage';
import Inplace from 'primevue/inplace';
import InputGroup from 'primevue/inputgroup';
import InputGroupAddon from 'primevue/inputgroupaddon';
import InputIcon from 'primevue/inputicon';
import InputMask from 'primevue/inputmask';
import InputNumber from 'primevue/inputnumber';
import InputSwitch from 'primevue/inputswitch';
import InputText from 'primevue/inputtext';
import Knob from 'primevue/knob';
import Listbox from 'primevue/listbox';
import MegaMenu from 'primevue/megamenu';
import Menu from 'primevue/menu';
import Menubar from 'primevue/menubar';
import Message from 'primevue/message';
import MultiSelect from 'primevue/multiselect';
import OrderList from 'primevue/orderlist';
import OrganizationChart from 'primevue/organizationchart';
import OverlayPanel from 'primevue/overlaypanel';
import Paginator from 'primevue/paginator';
import Panel from 'primevue/panel';
import PanelMenu from 'primevue/panelmenu';
import Password from 'primevue/password';
import PickList from 'primevue/picklist';
import ProgressBar from 'primevue/progressbar';
import ProgressSpinner from 'primevue/progressspinner';
import RadioButton from 'primevue/radiobutton';
import Rating from 'primevue/rating';
import Row from 'primevue/row';
import SelectButton from 'primevue/selectbutton';
import ScrollPanel from 'primevue/scrollpanel';
import ScrollTop from 'primevue/scrolltop';
import Slider from 'primevue/slider';
import Sidebar from 'primevue/sidebar';
import Skeleton from 'primevue/skeleton';
import SpeedDial from 'primevue/speeddial';
import SplitButton from 'primevue/splitbutton';
import Splitter from 'primevue/splitter';
import SplitterPanel from 'primevue/splitterpanel';
import Steps from 'primevue/steps';
import TabMenu from 'primevue/tabmenu';
import TabView from 'primevue/tabview';
import TabPanel from 'primevue/tabpanel';
import Tag from 'primevue/tag';
import Textarea from 'primevue/textarea';
import Terminal from 'primevue/terminal';
import TieredMenu from 'primevue/tieredmenu';
import Timeline from 'primevue/timeline';
import Toast from 'primevue/toast';
import Toolbar from 'primevue/toolbar';
import ToggleButton from 'primevue/togglebutton';
import Tree from 'primevue/tree';
import TreeSelect from 'primevue/treeselect';
import TreeTable from 'primevue/treetable';
import TriStateCheckbox from 'primevue/tristatecheckbox';
import VirtualScroller from 'primevue/virtualscroller';
import Tooltip from 'primevue/tooltip';
import BadgeDirective from 'primevue/badgedirective';
import Ripple from 'primevue/ripple';
import StyleClass from 'primevue/styleclass';
import ToastService from 'primevue/toastservice';
import DialogService from 'primevue/dialogservice';
import ConfirmationService from 'primevue/confirmationservice';
import { LoadingPlugin } from 'vue-loading-overlay';

export function useCustomComponents(app) {
    app.use(LoadingPlugin);
}

export function useComponents(app) {
    //PrimeVue components
    app.component('BlockViewer', BlockViewer);
    app.component('Accordion', Accordion);
    app.component('AccordionTab', AccordionTab);
    app.component('AutoComplete', AutoComplete);
    app.component('Avatar', Avatar);
    app.component('AvatarGroup', AvatarGroup);
    app.component('Badge', Badge);
    app.component('BlockUI', BlockUI);
    app.component('Breadcrumb', Breadcrumb);
    app.component('Button', Button);
    app.component('ButtonGroup', ButtonGroup);
    app.component('Calendar', Calendar);
    app.component('Card', Card);
    app.component('Chart', Chart);
    app.component('Carousel', Carousel);
    app.component('CascadeSelect', CascadeSelect);
    app.component('Checkbox', Checkbox);
    app.component('Chip', Chip);
    app.component('Chips', Chips);
    app.component('ColorPicker', ColorPicker);
    app.component('Column', Column);
    app.component('ColumnGroup', ColumnGroup);
    app.component('ConfirmDialog', ConfirmDialog);
    app.component('ConfirmPopup', ConfirmPopup);
    app.component('ContextMenu', ContextMenu);
    app.component('DataTable', DataTable);
    app.component('DataView', DataView);
    app.component('DataViewLayoutOptions', DataViewLayoutOptions);
    app.component('DeferredContent', DeferredContent);
    app.component('Dialog', Dialog);
    app.component('Divider', Divider);
    app.component('Dock', Dock);
    app.component('Dropdown', Dropdown);
    app.component('DynamicDialog', DynamicDialog);
    app.component('Fieldset', Fieldset);
    app.component('FileUpload', FileUpload);
    app.component('FloatLabel', FloatLabel);
    app.component('Galleria', Galleria);
    app.component('IconField', IconField);
    app.component('Image', Image);
    app.component('InlineMessage', InlineMessage);
    app.component('Inplace', Inplace);
    app.component('InputGroup', InputGroup);
    app.component('InputGroupAddon', InputGroupAddon);
    app.component('InputIcon', InputIcon);
    app.component('InputMask', InputMask);
    app.component('InputNumber', InputNumber);
    app.component('InputSwitch', InputSwitch);
    app.component('InputText', InputText);
    app.component('Knob', Knob);
    app.component('Listbox', Listbox);
    app.component('MegaMenu', MegaMenu);
    app.component('Menu', Menu);
    app.component('Menubar', Menubar);
    app.component('Message', Message);
    app.component('MultiSelect', MultiSelect);
    app.component('OrderList', OrderList);
    app.component('OrganizationChart', OrganizationChart);
    app.component('OverlayPanel', OverlayPanel);
    app.component('Paginator', Paginator);
    app.component('Panel', Panel);
    app.component('PanelMenu', PanelMenu);
    app.component('Password', Password);
    app.component('PickList', PickList);
    app.component('ProgressBar', ProgressBar);
    app.component('ProgressSpinner', ProgressSpinner);
    app.component('RadioButton', RadioButton);
    app.component('Rating', Rating);
    app.component('Row', Row);
    app.component('SelectButton', SelectButton);
    app.component('ScrollPanel', ScrollPanel);
    app.component('ScrollTop', ScrollTop);
    app.component('Slider', Slider);
    app.component('Sidebar', Sidebar);
    app.component('Skeleton', Skeleton);
    app.component('SpeedDial', SpeedDial);
    app.component('SplitButton', SplitButton);
    app.component('Splitter', Splitter);
    app.component('SplitterPanel', SplitterPanel);
    app.component('Steps', Steps);
    app.component('TabMenu', TabMenu);
    app.component('TabView', TabView);
    app.component('TabPanel', TabPanel);
    app.component('Tag', Tag);
    app.component('Textarea', Textarea);
    app.component('Terminal', Terminal);
    app.component('TieredMenu', TieredMenu);
    app.component('Timeline', Timeline);
    app.component('Toast', Toast);
    app.component('Toolbar', Toolbar);
    app.component('ToggleButton', ToggleButton);
    app.component('Tree', Tree);
    app.component('TreeSelect', TreeSelect);
    app.component('TreeTable', TreeTable);
    app.component('TriStateCheckbox', TriStateCheckbox);
    app.component('VirtualScroller', VirtualScroller);
}

export function useDirectives(app) {
    app.directive('tooltip', Tooltip);
    app.directive('badge', BadgeDirective);
    app.directive('ripple', Ripple);
    app.directive('styleclass', StyleClass);
}

export function useServices(app) {
    app.use(ToastService);
    app.use(DialogService);
    app.use(ConfirmationService);
}
