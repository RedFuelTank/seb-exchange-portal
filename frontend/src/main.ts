import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import 'chart.js/auto'
import 'chartjs-adapter-date-fns';

import { AppModule } from './app/app.module';

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
